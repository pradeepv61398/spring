package com.insurnace.customer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurnace.customer.EntityModel.HealthClaim;

@Repository
public interface HealthRepository  extends JpaRepository<HealthClaim, Long>{

}
