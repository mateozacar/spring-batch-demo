package com.example.demo.batch.listener;

import com.example.demo.model.RenewalPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

@Slf4j
public class PolicyWriterListener implements ItemWriteListener<RenewalPolicy> {

    @Override
    public void beforeWrite(List<? extends RenewalPolicy> list) {
        log.info(" {} records will be save into the database", list.size());
    }

    @Override
    public void afterWrite(List<? extends RenewalPolicy> list) {
        log.info(" {} records have been saved into the database", list.size());
    }

    @Override
    public void onWriteError(Exception e, List<? extends RenewalPolicy> list) {
        log.error(" {} records with problems in write process", list.size());
    }
}
