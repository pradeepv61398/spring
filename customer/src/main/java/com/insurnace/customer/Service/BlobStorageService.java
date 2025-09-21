package com.insurnace.customer.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Service
public class BlobStorageService {

    private final BlobContainerClient containerClient;

    @Autowired
     public BlobStorageService(
    //     @Value("${azure.storage.connection-string}") String connectionString,
    // //                           @Value("${azure.storage.container-name}") String containerName,
                               @Value("${spring.cloud.azure.storage.blob.connection-string}") String connectionString,
        @Value("${spring.cloud.azure.storage.blob.container-name}") String containerName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        containerClient = blobServiceClient.getBlobContainerClient(containerName);

        if (!containerClient.exists()) {
            containerClient.create();
        }
    }

    // Upload returns only the blobName
    public String uploadFile(MultipartFile file, Long claimId) throws IOException {
        String blobName = "claim-" + claimId + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        blobClient.upload(file.getInputStream(), file.getSize(), true);
        return blobName;
    }

    // Generate URL dynamically
    public String getBlobUrl(String blobName) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        return blobClient.getBlobUrl();
    }

    // Download file
    public byte[] downloadFile(String blobName) throws IOException {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blobClient.download(outputStream);
        return outputStream.toByteArray();
    }

    // Delete file
    public void deleteFile(String blobName) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        blobClient.delete();
    }
}

