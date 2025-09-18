package com.insurnace.customer.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.insurnace.customer.DTO.ClaimRequestDto;
import com.insurnace.customer.EntityModel.Claim;
import com.insurnace.customer.Service.BlobStorageService;
import com.insurnace.customer.Service.ClaimService;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private BlobStorageService blobStorageService;

   @PostMapping("/submit")
public ResponseEntity<?> submitClaim(
        @RequestPart("files") MultipartFile[] files,
        @RequestPart("claim") ClaimRequestDto claimRequest
) throws IOException {

    // 1. Upload files to Blob Storage and collect URLs
    List<String> blobUrls = new ArrayList<>();
    for (MultipartFile file : files) {
        String blobUrl = blobStorageService.uploadFile(file, claimRequest.getCustomerPolicyId());
        blobUrls.add(blobUrl);
    }

    // 2. Save claim with document URLs
    claimRequest.setDocumentUrl(blobUrls); // Make sure your DTO has a List<String> documentUrls
    Claim savedClaim = claimService.saveClaim(claimRequest);

    return ResponseEntity.ok(savedClaim);
}

}
