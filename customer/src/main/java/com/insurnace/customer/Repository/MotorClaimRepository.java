package com.insurnace.customer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurnace.customer.EntityModel.motorclaim;
@Repository
public interface MotorClaimRepository extends JpaRepository<motorclaim, Long>{

}
