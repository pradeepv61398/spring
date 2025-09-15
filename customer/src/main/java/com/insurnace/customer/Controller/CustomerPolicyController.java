package com.insurnace.customer.Controller;

import com.insurnace.customer.EntityModel.CustomerPolicy;
import com.insurnace.customer.EntityModel.PolicyPayment;
import com.insurnace.customer.Service.CustomerPolicyService;
import com.insurnace.customer.Service.PolicyPaymenytService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-policies")
public class CustomerPolicyController {
  @Autowired
  PolicyPaymenytService policyPaymenytService;
    private final CustomerPolicyService customerPolicyService;

    public CustomerPolicyController(CustomerPolicyService customerPolicyService) {
        this.customerPolicyService = customerPolicyService;
    }

    @PostMapping("/buy")
    public CustomerPolicy buyPolicy(@RequestParam Long customerId, @RequestParam Long policyId) {
        return customerPolicyService.buyPolicy(customerId, policyId);
    }

    @GetMapping("/{customerId}")
    public List<CustomerPolicy> getPolicies(@PathVariable Long customerId) {
        return customerPolicyService.getCustomerPolicies(customerId);
    }
    @GetMapping("/payments/{customerPolicyId}")
public List<PolicyPayment> getPayments(@PathVariable Long customerPolicyId) {
    return policyPaymenytService.getPaymentsByCustomerPolicy(customerPolicyId);
}

@PostMapping("/payments/pay/{paymentId}")
public PolicyPayment payInstallment(@PathVariable Long paymentId) {
    return policyPaymenytService.payInstallment(paymentId);
}

}
