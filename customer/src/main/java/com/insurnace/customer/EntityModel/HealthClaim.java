package com.insurnace.customer.EntityModel;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "health_claim")
@Entity
public class HealthClaim {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;

    private String hospitalName;
    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String patientName;
    private String doctorName;
    public void setId(Long id) {
        this.id = id;
    }
    public void setClaim(Claim claim) {
        this.claim = claim;
    }
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }
    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    
    public Long getId() {
        return id;
    }
    public Claim getClaim() {
        return claim;
    }
    public String getHospitalName() {
        return hospitalName;
    }
    public LocalDate getAdmissionDate() {
        return admissionDate;
    }
    public LocalDate getDischargeDate() {
        return dischargeDate;
    }
    public String getPatientName() {
        return patientName;
    }
    public String getDoctorName() {
        return doctorName;
    }
    
}
