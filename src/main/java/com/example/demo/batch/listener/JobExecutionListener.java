package com.example.demo.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
@Slf4j
public class JobExecutionListener implements org.springframework.batch.core.JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("----Job will start soon----");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("----Job has been finished the status is {}----", jobExecution.getStatus());
    }
}
