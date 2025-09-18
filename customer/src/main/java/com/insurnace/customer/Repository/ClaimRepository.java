package com.insurnace.customer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurnace.customer.EntityModel.Claim;
@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

}
