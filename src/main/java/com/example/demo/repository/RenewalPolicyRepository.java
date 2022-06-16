package com.example.demo.repository;

import com.example.demo.model.RenewalPolicy;
import org.springframework.data.repository.CrudRepository;

public interface RenewalPolicyRepository extends CrudRepository<RenewalPolicy, Integer> {
}
