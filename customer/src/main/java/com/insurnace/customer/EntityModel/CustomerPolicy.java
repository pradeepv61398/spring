package com.insurnace.customer.EntityModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class CustomerPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;
    private String status; 
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "customerPolicy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
private List<PolicyPayment> payments = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public Policy getPolicy() {
        return policy;
    }
    public String getStatus() {
        return status;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public List<PolicyPayment> getPayments() {
        return payments;
    }
    public void setPayments(List<PolicyPayment> payments) {
        this.payments = payments;
    }
    public void addPayment(PolicyPayment payment) {
        payments.add(payment);
    }
    public void removePayment(PolicyPayment payment) {
        payments.remove(payment);
    }


    



}
