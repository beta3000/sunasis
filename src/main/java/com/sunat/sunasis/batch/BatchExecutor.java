package com.sunat.sunasis.batch;

import com.sunat.sunasis.batch.company.CompanyProcessor;
import com.sunat.sunasis.batch.company.CompanyWriter;
import com.sunat.sunasis.batch.files.FileCleanTasklet;
import com.sunat.sunasis.batch.listener.ChunkExecutionListener;
import com.sunat.sunasis.batch.listener.JobCompletionNotificationListener;
import com.sunat.sunasis.batch.listener.StepExecutionNotificationListener;
import com.sunat.sunasis.model.Company;
import com.sunat.sunasis.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.io.File;

@Configuration
@EnableBatchProcessing
public class BatchExecutor extends DefaultBatchConfigurer {

    public final static Logger logger = LoggerFactory.getLogger(BatchExecutor.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    FileCleanTasklet fileCleanTasklet;

    @Autowired
    public CompanyProcessor companyProcessor;

    @Autowired
    public CompanyWriter companyWriter;

    @Bean
    public JobCompletionNotificationListener jobExecutionListener() {
        return new JobCompletionNotificationListener();
    }

    @Bean
    public StepExecutionNotificationListener stepExecutionListener() {
        return new StepExecutionNotificationListener();
    }

    @Bean
    public ChunkExecutionListener chunkListener() {
        return new ChunkExecutionListener();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(AppUtils.MAX_THREADS);
        return taskExecutor;
    }

    @Bean
    public Job process() {
        logger.info("Runtime: process");
        return jobBuilderFactory
                .get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener())
                .start(companies())
                .next(files())
                .build();
    }

    @Bean
    public Step companies() {
        logger.info("Runtime: step companies");
        return stepBuilderFactory.get("ETL-Company-Load").<Company, Company>chunk(AppUtils.CHUNK_ZISE_COMPANIES)
                .reader(companyReader())
                .processor(companyProcessor)
                .writer(companyWriter)
                .taskExecutor(taskExecutor())
                .listener(stepExecutionListener())
                .listener(chunkListener())
                .throttleLimit(AppUtils.MAX_THREADS)
                .build();
    }

    @Bean
    public Step files() {
        return stepBuilderFactory.get("ETL-Files-Clean")
                .tasklet(fileCleanTasklet)
                .build();
    }

    @Bean
    public ItemReader<Company> companyReader() {
        logger.info("Runtime: read Companies");
        FlatFileItemReader<Company> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(System.getProperty("user.dir") + File.separator + AppUtils.UNZIP_FILE_NAME));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<Company>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(AppUtils.SEPARATOR);
                setStrict(false);
                setNames(new String[]{"ruc", "razonSocial", "estado", "condicion", "ubigeo", "tipoVia", "nombreVia", "codigoZona", "tipoZona", "numero", "interior", "lote", "departamento", "manzana", "kilometro"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Company>() {{
                setTargetType(Company.class);
            }});
        }});
        return reader;
    }
}
