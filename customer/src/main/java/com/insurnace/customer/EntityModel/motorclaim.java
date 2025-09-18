package com.insurnace.customer.EntityModel;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "motor_claim")
public class motorclaim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;

    private String vehicleNumber;
    private LocalDate accidentDate;
    private String location;
    private String firNumber;
    private String garageName;
    public Long getId() {
        return id;
    }
    public Claim getClaim() {
        return claim;
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
    public void setId(Long id) {
        this.id = id;
    }
    public void setClaim(Claim claim) {
        this.claim = claim;
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

    

}
