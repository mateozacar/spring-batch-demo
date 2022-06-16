package com.example.demo.batch.config;

import com.example.demo.batch.PolicyProcess;
import com.example.demo.batch.PolicyReader;
import com.example.demo.batch.PolicyWriter;
import com.example.demo.batch.listener.JobExecutionListener;
import com.example.demo.batch.listener.PolicyProcessListener;
import com.example.demo.batch.listener.PolicyReaderListener;
import com.example.demo.batch.listener.PolicyWriterListener;
import com.example.demo.service.Notification;
import com.example.demo.model.Policy;
import com.example.demo.model.RenewalPolicy;
import com.example.demo.repository.PolicyRepository;
import com.example.demo.repository.RenewalPolicyRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobBatchConfiguration {

    private final PolicyRepository policyRepository;
    private final RenewalPolicyRepository renewalPolicyRepository;
    private final Notification emailNotification;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    @Autowired
    public JobBatchConfiguration(PolicyRepository policyRepository, RenewalPolicyRepository renewalPolicyRepository, Notification emailNotification, StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory) {
        this.policyRepository = policyRepository;
        this.renewalPolicyRepository = renewalPolicyRepository;
        this.emailNotification = emailNotification;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public PolicyReader reader() {
        return new PolicyReader(this.policyRepository);
    }

    @Bean
    public PolicyProcess process() {
        return new PolicyProcess();
    }

    @Bean
    public PolicyWriter writer() {
        return new PolicyWriter(this.renewalPolicyRepository, this.emailNotification);
    }

    // Listeners

    @Bean
    public JobExecutionListener jobExecutionListener () {
        return new JobExecutionListener();
    }

    @Bean
    public PolicyReaderListener policyReaderListener() {
        return new PolicyReaderListener();
    }

    @Bean
    public PolicyProcessListener policyProcessListener() {
        return new PolicyProcessListener();
    }

    @Bean
    public PolicyWriterListener policyWriterListener() {
        return new PolicyWriterListener();
    }

    //Step

    @Bean
    public Step step(PolicyReader policyReader,
                     PolicyProcess policyProcess,
                     PolicyWriter policyWriter,
                     PolicyWriterListener policyWriterListener,
                     PolicyProcessListener policyProcessListener,
                     PolicyReaderListener policyReaderListener){
        return this.stepBuilderFactory
                .get("Step1")
                .<Policy, RenewalPolicy>chunk(100)
                .reader(policyReader)
                .processor(policyProcess)
                .writer(policyWriter)
                .listener(policyProcessListener)
                .listener(policyReaderListener)
                .listener(policyWriterListener)
                .build();

    }


    //Job
    @Bean
    public Job job(Step step, JobExecutionListener jobExecutionListener){
        return this.jobBuilderFactory
                .get("Job1")
                .listener(jobExecutionListener)
                .flow(step)
                .end()
                .build();
    }
}
