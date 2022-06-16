package com.example.demo.batch;

import com.example.demo.service.Notification;
import com.example.demo.model.RenewalPolicy;
import com.example.demo.repository.RenewalPolicyRepository;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


public class PolicyWriter implements ItemWriter<RenewalPolicy> {

    private final RenewalPolicyRepository renewalPolicyRepository;
    private final Notification emailNotification;

    public PolicyWriter(RenewalPolicyRepository renewalPolicyRepository, Notification emailNotification) {
        this.renewalPolicyRepository = renewalPolicyRepository;
        this.emailNotification = emailNotification;
    }

    @Override
    public void write(List<? extends RenewalPolicy> renewalPolicyList) {
        renewalPolicyList.forEach( renewalPolicy -> {
            renewalPolicyRepository.save(renewalPolicy);
            emailNotification.send("The policy :" + renewalPolicy.getPolicyId() + "has been renewed");
        });
    }
}
