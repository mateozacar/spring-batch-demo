package com.example.demo.repository;

import com.example.demo.model.Policy;
import org.springframework.data.repository.CrudRepository;

public interface PolicyRepository extends CrudRepository<Policy, Integer> {
}
