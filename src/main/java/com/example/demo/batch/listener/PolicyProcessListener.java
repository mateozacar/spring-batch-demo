package com.example.demo.batch.listener;

import com.example.demo.model.Policy;
import com.example.demo.model.RenewalPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
@Slf4j
public class PolicyProcessListener implements ItemProcessListener<Policy, RenewalPolicy> {

    @Override
    public void beforeProcess(Policy policy) {
        log.info("The next policy id {} will be the next one to process", policy.getId());
    }

    @Override
    public void afterProcess(Policy policy, RenewalPolicy renewalPolicy) {
        log.info("The next policy id {} the renew status is : {}", policy.getId(), renewalPolicy.getPolicyRenewed());
    }

    @Override
    public void onProcessError(Policy policy, Exception e) {
        log.error("Error processing the policy id {} ", policy.getId(), e.getCause());
    }
}
