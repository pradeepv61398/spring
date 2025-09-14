package com.insurnace.customer.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurnace.customer.EntityModel.Policy;
import com.insurnace.customer.Service.PolicyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/policy")
public class PolicyController {
    @Autowired
    PolicyService policyService;

   @GetMapping("/getAllPolices")
   public ResponseEntity<List<Policy>> getMethodName() {
    List<Policy> policy=policyService.getAllPolicies();
    return ResponseEntity.status(200).body(policy);
   }

   @PostMapping("/createPolicy")
   public ResponseEntity<Policy> createPolicy(@RequestBody Policy entity) {
       Policy create=policyService.createPolicy(entity);
       return ResponseEntity.status(200).body(entity);
    
   }
   
   

}
