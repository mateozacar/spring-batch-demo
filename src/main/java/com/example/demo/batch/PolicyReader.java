package com.example.demo.batch;

import com.example.demo.model.Policy;
import com.example.demo.repository.PolicyRepository;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;

import java.util.Iterator;


public class PolicyReader implements ItemReader<Policy> {

    private final PolicyRepository policyRepository;
    private Iterator<Policy> policyIterator;

    public PolicyReader(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @BeforeStep
    public void before() {
        this.policyIterator =  this.policyRepository.findAll().iterator();
    }

    @Override
    public Policy read()  {
        if(this.policyIterator != null && policyIterator.hasNext()) {
            return this.policyIterator.next();
        }
        return null;

        /** Why we return null here ?
         * Because in SimpleChuckProvider has this validation to know when the list will be finish according to the chunk.
            if (item == null) {
            inputs.setEnd();
            return RepeatStatus.FINISHED;
         **/
    }
}
