package com.sunat.sunasis.batch.files;

import com.sunat.sunasis.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class FileCleanTasklet implements Tasklet {

    public final static Logger logger = LoggerFactory.getLogger(FileCleanTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        long time = System.currentTimeMillis();
        logger.info("Runtime FilesCleanReader: {} seconds.", ((double) time / 1000));

        Path pathZip = Paths.get(AppUtils.FILE_NAME);
        if (Files.exists(pathZip)) {
            Files.delete(pathZip);
        }
        Path pathFile = Paths.get(AppUtils.UNZIP_FILE_NAME);
        if (Files.exists(pathFile)) {
            Files.delete(pathFile);
        }

        return null;
    }
}