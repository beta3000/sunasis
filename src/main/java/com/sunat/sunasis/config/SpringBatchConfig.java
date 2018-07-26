package com.sunat.sunasis.config;

import com.sunat.sunasis.model.CompanyModel;
import com.sunat.sunasis.utils.AppUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;


import javax.batch.runtime.BatchStatus;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<CompanyModel> companyReader,
                   ItemProcessor<CompanyModel, CompanyModel> companyProcessor,
                   ItemWriter<CompanyModel> companyWriter
    ) {

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<CompanyModel, CompanyModel>chunk(50000)
                .reader(companyReader)
                .processor(companyProcessor)
                .writer(companyWriter)
                .build();


        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public ItemReader<CompanyModel> reader()  throws Exception{
        download();
        unzip();
        FlatFileItemReader<CompanyModel> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(System.getProperty("user.dir") + File.separator + AppUtils.UNZIP_FILE_NAME));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<CompanyModel>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(AppUtils.SEPARATOR);
                setStrict(false);
                setNames(new String[] {"ruc", "razonSocial", "estado", "condicion", "ubigeo", "tipoVia", "nombreVia", "codigoZona", "tipoZona", "numero", "interior", "lote", "departamento", "manzana", "kilometro"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CompanyModel>() {{
                setTargetType(CompanyModel.class);
            }});
        }});
        return reader;
    }

    private String download() throws Exception {
        URLConnection urlCon = new URL(AppUtils.URL_FILE).openConnection();
        InputStream is = urlCon.getInputStream();
        FileOutputStream fos = new FileOutputStream(AppUtils.FILE_NAME);
        byte[] buffer = new byte[1000];
        int bytesRead = is.read(buffer);

        while (bytesRead > 0) {
            fos.write(buffer, 0, bytesRead);
            bytesRead = is.read(buffer);
        }
        is.close();
        fos.close();

        return BatchStatus.COMPLETED.toString();
    }

    private String unzip() throws Exception {
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(AppUtils.FILE_NAME));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = new File(AppUtils.UNZIP_FILE_NAME);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        deleteZip();
        return BatchStatus.COMPLETED.toString();
    }

    private String deleteZip() throws Exception {
        Path path = Paths.get(AppUtils.FILE_NAME);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        return BatchStatus.COMPLETED.toString();
    }
}
