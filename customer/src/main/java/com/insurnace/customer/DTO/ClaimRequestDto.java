package com.insurnace.customer.DTO;

import java.time.LocalDate;
import java.util.List;

public class ClaimRequestDto {
    private Long customerPolicyId; ;
    private String claimType; // HEALTH, CAR, LIFE
    private Double amount;
    private String reason;

    // Health fields
    private String hospitalName;
    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String patientName;
    private String doctorName;

    // Car fields
    private String vehicleNumber;
    private LocalDate accidentDate;
    private String location;
    private String firNumber;
    private String garageName;

    // Life fields
    private String nomineeName;
    private String relationship;
    private String deathCertificateNumber;
    private LocalDate dateOfDeath;
    private List<String> documentUrls; // New field for document URL
   
    public String getClaimType() {
        return claimType;
    }
    public Double getAmount() {
        return amount;
    }
    public String getReason() {
        return reason;
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
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public LocalDate getAccidentDate() {
        return accidentDate;
    }
    public String getLocation() {
        return location;
    }
    public String getFirNumber() {
        return firNumber;
    }
    public String getGarageName() {
        return garageName;
    }
    public String getNomineeName() {
        return nomineeName;
    }
    public String getRelationship() {
        return relationship;
    }
    public String getDeathCertificateNumber() {
        return deathCertificateNumber;
    }
    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }
    
    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public void setReason(String reason) {
        this.reason = reason;
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
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public void setAccidentDate(LocalDate accidentDate) {
        this.accidentDate = accidentDate;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setFirNumber(String firNumber) {
        this.firNumber = firNumber;
    }
    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }
    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    public void setDeathCertificateNumber(String deathCertificateNumber) {
        this.deathCertificateNumber = deathCertificateNumber;
    }
    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
    public Long getCustomerPolicyId() {
        return customerPolicyId;
    }
    public void setCustomerPolicyId(Long customerPolicyId) {
        this.customerPolicyId = customerPolicyId;
    }
    public List<String> getDocumentUrl() {
        return documentUrls;
    }
    public void setDocumentUrl(List<String> documentUrl) {
        this.documentUrls = documentUrl;
    }

    


    
}



