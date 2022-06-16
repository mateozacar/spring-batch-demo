package com.example.demo.batch.listener;

import com.example.demo.model.Policy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

@Slf4j
public class PolicyReaderListener implements ItemReadListener<Policy> {
    @Override
    public void beforeRead() {
        log.info("1- Starting process, Reading data from database.");
    }

    @Override
    public void afterRead(Policy policy) {
        log.info("Policy id {} read from database: ", policy.getId());
    }

    @Override
    public void onReadError(Exception e) {
        log.error("Error reading policy from database : " , e.getMessage());
    }
}
