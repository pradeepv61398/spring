package com.insurnace.customer.EntityModel;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.insurnace.customer.Enum.PaymentStatus;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PolicyPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_policy_id")
    private CustomerPolicy customerPolicy;

    private int installmentYear; // 1,2,3...
    private double amount;
    private LocalDate dueDate;
    private boolean paid;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private LocalDate paymentDate;


    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public CustomerPolicy getCustomerPolicy() {
        return customerPolicy;
    }
    public void setCustomerPolicy(CustomerPolicy customerPolicy) {
        this.customerPolicy = customerPolicy;
    }


    public int getInstallmentYear() {
        return installmentYear;
    }

    public void setInstallmentYear(int installmentYear) {
        this.installmentYear = installmentYear;
    }


    public double getAmount() {
        return amount;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }


    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    public boolean isPaid() {
        return paid;
    }


    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    public LocalDate getPaymentDate() {
        return paymentDate;
        }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }   
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }



}



