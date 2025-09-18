package com.insurnace.customer.Controller;

import com.insurnace.customer.EntityModel.CustomerPolicy;
import com.insurnace.customer.EntityModel.PolicyPayment;
import com.insurnace.customer.Service.CustomerPolicyService;
import com.insurnace.customer.Service.PolicyPaymenytService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-policies")
public class CustomerPolicyController {

    @Autowired
    private PolicyPaymenytService policyPaymenytService;

    private final CustomerPolicyService customerPolicyService;

    public CustomerPolicyController(CustomerPolicyService customerPolicyService) {
        this.customerPolicyService = customerPolicyService;
    }

    @PostMapping("/buy")
    public ResponseEntity<CustomerPolicy> buyPolicy(@RequestParam Long customerId, @RequestParam Long policyId) {
        CustomerPolicy purchasedPolicy = customerPolicyService.buyPolicy(customerId, policyId);
        if (purchasedPolicy != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(purchasedPolicy);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CustomerPolicy>> getPolicies(@PathVariable Long customerId) {
        List<CustomerPolicy> policies = customerPolicyService.getCustomerPolicies(customerId);
        if (policies != null && !policies.isEmpty()) {
            return ResponseEntity.ok(policies);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/payments/{customerPolicyId}")
    public ResponseEntity<List<PolicyPayment>> getPayments(@PathVariable Long customerPolicyId) {
        List<PolicyPayment> payments = policyPaymenytService.getPaymentsByCustomerPolicy(customerPolicyId);
        if (payments != null && !payments.isEmpty()) {
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/payments/pay/{paymentId}")
    public ResponseEntity<PolicyPayment> payInstallment(@PathVariable Long paymentId) {
        PolicyPayment payment = policyPaymenytService.payInstallment(paymentId);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
