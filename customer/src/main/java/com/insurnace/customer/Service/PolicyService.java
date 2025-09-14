package com.insurnace.customer.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurnace.customer.EntityModel.Policy;
import com.insurnace.customer.Repository.PolicyRepository;
@Service
public class PolicyService {
    @Autowired
    PolicyRepository policyRepository;


public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }
}
