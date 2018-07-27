package com.sunat.sunasis.batch.listener;

import com.sunat.sunasis.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        try {
            download();
            unzip();
            super.beforeJob(jobExecution);
            logger.info("Job Started");
        } catch (Exception ex) {
            logger.info("Job error start");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("Job Completed");
        }
    }

    private String download() throws Exception {
        long time = System.currentTimeMillis();
        logger.info("Runtime FilesDownloadReader: {} seconds.", ((double) time / 1000));

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
        return javax.batch.runtime.BatchStatus.COMPLETED.toString();
    }

    private String unzip() throws Exception {
        long time = System.currentTimeMillis();
        logger.info("Runtime FilesUnZipProcessor: {} seconds.", ((double) time / 1000));

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
        return javax.batch.runtime.BatchStatus.COMPLETED.toString();
    }
}