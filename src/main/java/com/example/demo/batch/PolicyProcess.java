package com.example.demo.batch;

import com.example.demo.model.Policy;
import com.example.demo.model.RenewalPolicy;
import org.springframework.batch.item.ItemProcessor;

public class PolicyProcess implements ItemProcessor<Policy, RenewalPolicy> {

    @Override
    public RenewalPolicy process(Policy policy){
        if(policy.getRate() > 5.6) {
            return new RenewalPolicy(policy.getId(), Boolean.TRUE);
        }
        return new RenewalPolicy(policy.getId(), Boolean.FALSE);
    }
}
