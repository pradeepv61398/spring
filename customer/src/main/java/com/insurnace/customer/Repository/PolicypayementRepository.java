package com.insurnace.customer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurnace.customer.EntityModel.PolicyPayment;

@Repository
public interface PolicypayementRepository extends JpaRepository<PolicyPayment, Long> {
    List<PolicyPayment> findByCustomerPolicyId(Long customerPolicyId);

}
