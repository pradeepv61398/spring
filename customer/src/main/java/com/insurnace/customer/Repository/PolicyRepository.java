package com.insurnace.customer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurnace.customer.EntityModel.Policy;
import com.insurnace.customer.EntityModel.User;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {


}
