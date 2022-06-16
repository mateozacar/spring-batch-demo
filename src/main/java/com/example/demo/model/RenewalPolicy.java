package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RenewalPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long policyId;
    private Boolean policyRenewed;

    public RenewalPolicy(Long policyId, Boolean policyRenewed) {
        this.policyId = policyId;
        this.policyRenewed = policyRenewed;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public Boolean getPolicyRenewed() {
        return policyRenewed;
    }

    public void setPolicyRenewed(Boolean policyRenewed) {
        this.policyRenewed = policyRenewed;
    }
}
