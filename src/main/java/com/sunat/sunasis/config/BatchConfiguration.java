package com.sunat.sunasis.config;

import com.sunat.sunasis.utils.AppUtils;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Scheduled(cron = "0 0 4 * * *")
    public BatchStatus process() throws Exception {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);
        System.out.println("JobExecution: " + jobExecution.getStatus());
        System.out.println("Batch is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }
        deleteFile();
        return jobExecution.getStatus();
    }

    private String deleteFile() throws Exception {
        Path path = Paths.get(AppUtils.UNZIP_FILE_NAME);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        return javax.batch.runtime.BatchStatus.COMPLETED.toString();
    }
}
