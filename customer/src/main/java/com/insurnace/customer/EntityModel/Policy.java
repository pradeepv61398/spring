package com.insurnace.customer.EntityModel;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String policyNumber; // Unique identifier
    private String name;
    private double premium;
    private String type; //Type	
   private String coverage; // 

    private int term; // in months
    private String description;
    public Long getId() {
        return id;
    }
    public String getPolicyNumber() {
        return policyNumber;
    }
    public String getName() {
        return name;
    }
    public double getPremium() {
        return premium;
    }
    public int getTerm() {
        return term;
    }
    public String getDescription() {
        return description;
    }
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPremium(double premium) {
        this.premium = premium;
    }
    public void setTerm(int term) {
        this.term = term;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
   public String getCoverage() {
        return coverage;
    }
    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }
    

}
