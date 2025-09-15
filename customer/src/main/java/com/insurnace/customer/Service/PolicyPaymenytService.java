package com.insurnace.customer.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurnace.customer.EntityModel.PolicyPayment;
import com.insurnace.customer.Enum.PaymentStatus;
import com.insurnace.customer.Repository.PolicypayementRepository;

@Service
public class PolicyPaymenytService {
    @Autowired
    PolicypayementRepository policyPaymentRepository;

    // @Autowired
    // PolicyPayment payment;

    public List<PolicyPayment> getPaymentsByCustomerPolicy(Long customerPolicyId) {
        return policyPaymentRepository.findByCustomerPolicyId(customerPolicyId);
    }

    public PolicyPayment payInstallment(Long paymentId) {
        PolicyPayment payment = policyPaymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setPaid(true);
        payment.setStatus(PaymentStatus.PAID);
        payment.setPaymentDate(LocalDate.now());
        return policyPaymentRepository.save(payment);
    }

}
