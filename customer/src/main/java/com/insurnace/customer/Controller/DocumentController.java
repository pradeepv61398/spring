package com.insurnace.customer.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.insurnace.customer.Service.BlobStorageService;


@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private BlobStorageService storageService;

    @PostMapping("/upload/{claimId}")
    public ResponseEntity<String> upload(@PathVariable Long claimId,
                                         @RequestParam("file") MultipartFile file) throws IOException {
        String blobName = storageService.uploadFile(file, claimId);
        return ResponseEntity.ok("File uploaded successfully. Blob Name: " + blobName);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String blobName) throws IOException {
        byte[] data = storageService.downloadFile(blobName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + blobName.substring(blobName.lastIndexOf("/") + 1) + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String blobName) {
        storageService.deleteFile(blobName);
        return ResponseEntity.ok("File deleted: " + blobName);
    }
    @GetMapping("/url")
    public ResponseEntity<String> getFileUrl(@RequestParam String blobName) {
        String url = storageService.getBlobUrl(blobName);
        return ResponseEntity.ok(url);
    }

}