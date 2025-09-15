package com.insurnace.customer.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurnace.customer.EntityModel.CustomerPolicy;
import com.insurnace.customer.EntityModel.Policy;
import com.insurnace.customer.EntityModel.PolicyPayment;
import com.insurnace.customer.Enum.PaymentStatus;
import com.insurnace.customer.Repository.CustomerPolicyRepository;
import com.insurnace.customer.Repository.PolicyRepository;
import com.insurnace.customer.Repository.PolicypayementRepository;
import com.insurnace.customer.Repository.UserRepository;

@Service
public class CustomerPolicyService {
    @Autowired
   private CustomerPolicyRepository customerPolicyRepository;
   @Autowired
   private PolicyRepository policyRepository; 
  
   @Autowired
   private PolicypayementRepository policyPaymentRepository;
  

//    public CustomerPolicy buyPolicy(Long customerId, Long policyId) {
//         Policy policy = policyRepository.findById(policyId)
//                 .orElseThrow(() -> new RuntimeException("Policy not found"));

//         CustomerPolicy customerPolicy = new CustomerPolicy();
//         customerPolicy.setCustomerId(customerId);
//         customerPolicy.setPolicy(policy);
//         customerPolicy.setStartDate(LocalDate.now());
//         customerPolicy.setEndDate(LocalDate.now().plusYears(policy.getTerm()));
//         customerPolicy.setStatus("ACTIVE");
//         CustomerPolicy savedCustomerPolicy = customerPolicyRepository.save(customerPolicy);

//         // // 4. Generate payment schedule (yearly installments)
//         List<PolicyPayment> payments = new ArrayList<>();
//         for (int i = 1; i <= policy.getTerm(); i++) {
//             // PolicyPayment payment = new PolicyPayment();
//             payment.setCustomerPolicy(savedCustomerPolicy);
//            payment.setInstallmentYear(i);
//            payment.setAmount(policy.getPremium());
//            payment.setDueDate(startDate.plusYears(i - 1)); // yearly due date
//             payment.setStatus(PaymentStatus.PENDING);
//             payments.add(payment);
//          }

//          policyPaymentRepository.saveAll(payments);

//         return savedCustomerPolicy;

//       //  return customerPolicyRepository.save(customerPolicy);

// }
public CustomerPolicy buyPolicy(Long customerId, Long policyId) {
    Policy policy = policyRepository.findById(policyId)
            .orElseThrow(() -> new RuntimeException("Policy not found"));

    // 1. Create and save CustomerPolicy
    CustomerPolicy customerPolicy = new CustomerPolicy();
    customerPolicy.setCustomerId(customerId);
    customerPolicy.setPolicy(policy);
    customerPolicy.setStartDate(LocalDate.now());
    customerPolicy.setEndDate(LocalDate.now().plusYears(policy.getTerm()));
    customerPolicy.setStatus("ACTIVE");
    CustomerPolicy savedCustomerPolicy = customerPolicyRepository.save(customerPolicy);

    // 2. Generate payment schedule (yearly installments)
    List<PolicyPayment> payments = new ArrayList<>();
    LocalDate startDate = savedCustomerPolicy.getStartDate();

    for (int i = 1; i <= policy.getTerm(); i++) {
        PolicyPayment payment = new PolicyPayment(); // ✅ create new instance each loop
        payment.setCustomerPolicy(savedCustomerPolicy);
        payment.setInstallmentYear(i);
        payment.setAmount(policy.getPremium());
        payment.setDueDate(startDate.plusYears(i - 1)); // yearly due date
        payment.setStatus(PaymentStatus.PENDING); // ✅ enum usage
        payments.add(payment);
    }

    policyPaymentRepository.saveAll(payments);

    return savedCustomerPolicy;
}

 public List<CustomerPolicy> getCustomerPolicies(Long customerId) {
        return customerPolicyRepository.findByCustomerId(customerId);
    }

}