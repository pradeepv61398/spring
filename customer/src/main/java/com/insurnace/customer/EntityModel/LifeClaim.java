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
@Table(name = "life_claims")
public class LifeClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;

    private String nomineeName;
    private String relationship;
    private String deathCertificateNumber;
    private LocalDate dateOfDeath;
    public Long getId() {
        return id;
    }
    public Claim getClaim() {
        return claim;
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
    public void setId(Long id) {
        this.id = id;
    }
    public void setClaim(Claim claim) {
        this.claim = claim;
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

    // getters and setters
    
}
