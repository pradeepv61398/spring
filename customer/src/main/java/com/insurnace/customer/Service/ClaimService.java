package com.insurnace.customer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurnace.customer.DTO.ClaimRequestDto;
import com.insurnace.customer.EntityModel.Claim;
import com.insurnace.customer.EntityModel.CustomerPolicy;
import com.insurnace.customer.EntityModel.HealthClaim;
import com.insurnace.customer.EntityModel.LifeClaim;
import com.insurnace.customer.EntityModel.motorclaim;
import com.insurnace.customer.EntityModel.motorclaim;
import com.insurnace.customer.Repository.ClaimRepository;
import com.insurnace.customer.Repository.CustomerPolicyRepository;
import com.insurnace.customer.Repository.HealthRepository;
import com.insurnace.customer.Repository.LifeRepository;
import com.insurnace.customer.Repository.MotorClaimRepository;

@Service
public class ClaimService {
    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private HealthRepository healthRepository;
    @Autowired
    private LifeRepository lifeRepository;
     @Autowired
    private MotorClaimRepository motorRepository;
    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;
    
public Claim saveClaim(ClaimRequestDto request) {
        // Save base claim
        CustomerPolicy customerPolicy = customerPolicyRepository.findById(request.getCustomerPolicyId())
        .orElseThrow(() -> new RuntimeException("Customer policy not found"));

        Claim claim = new Claim();
        claim.setCustomerPolicy(customerPolicy);
        claim.setClaimType(request.getClaimType());
        claim.setAmount(request.getAmount());
        claim.setReason(request.getReason());
        claim.setStatus("PENDING");

        Claim savedClaim = claimRepository.save(claim);

        // Save type-specific details
        switch (request.getClaimType().toUpperCase()) {
            case "HEALTH":
                HealthClaim hc = new HealthClaim();
                hc.setClaim(savedClaim);
                hc.setHospitalName(request.getHospitalName());
                hc.setAdmissionDate(request.getAdmissionDate());
                hc.setDischargeDate(request.getDischargeDate());
                hc.setPatientName(request.getPatientName());
                hc.setDoctorName(request.getDoctorName());
                healthRepository.save(hc);
                break;

            case "Motor":
            // FIR check logic
                String reason = request.getReason() != null ? request.getReason().toLowerCase() : "";

                boolean isCritical = reason.contains("theft") || reason.contains("injury");
                if (isCritical && (request.getFirNumber() == null || request.getFirNumber().isEmpty())) {
                    throw new IllegalArgumentException("FIR is mandatory for theft or injury-related car claims.");
                }
                motorclaim cc = new motorclaim();
                cc.setClaim(savedClaim);
                cc.setVehicleNumber(request.getVehicleNumber());
                cc.setAccidentDate(request.getAccidentDate());
                cc.setLocation(request.getLocation());
                cc.setFirNumber(request.getFirNumber());
                cc.setGarageName(request.getGarageName());
                motorRepository.save(cc);
                break;

            case "LIFE":
                LifeClaim lc = new LifeClaim();
                lc.setClaim(savedClaim);
                lc.setNomineeName(request.getNomineeName());
                lc.setRelationship(request.getRelationship());
                lc.setDeathCertificateNumber(request.getDeathCertificateNumber());
                lc.setDateOfDeath(request.getDateOfDeath());
                lifeRepository.save(lc);
                break;
        }

        return savedClaim;
    }

   

}
