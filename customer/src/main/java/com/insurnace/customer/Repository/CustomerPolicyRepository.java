package com.insurnace.customer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurnace.customer.EntityModel.CustomerPolicy;
import com.insurnace.customer.EntityModel.Policy;

@Repository
public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Long> {

    // You can add custom query methods here if needed
    List<CustomerPolicy> findByCustomerId(Long customerId);
    

}
