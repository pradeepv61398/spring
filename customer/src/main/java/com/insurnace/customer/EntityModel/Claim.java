package com.insurnace.customer.EntityModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "claimscso")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    
    private String claimType; // HEALTH, CAR, LIFE
    private Double amount;
    private String reason;
    private String status = "PENDING";
    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }
     @ManyToOne
    @JoinColumn(name="customer_policy_id")
    private CustomerPolicy customerPolicy;
    
    

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    @CreationTimestamp
    private LocalDateTime submittedAt;

    public CustomerPolicy getCustomerPolicy() {
        return customerPolicy;
    }

    public void setCustomerPolicy(CustomerPolicy customerPolicy) {
        this.customerPolicy = customerPolicy;
    }


    public Long getId() {
        return id;
    }

  

    public String getClaimType() {
        return claimType;
    }

    public Double getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    public List<Document> getDocuments() {
        return documents;
    }
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    


}
