package com.insurnace.customer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurnace.customer.EntityModel.LifeClaim;

@Repository
public interface LifeRepository extends JpaRepository<LifeClaim, Long>{

}
