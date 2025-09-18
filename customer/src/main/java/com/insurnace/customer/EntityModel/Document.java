package com.insurnace.customer.EntityModel;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;     // original filename
    private String blobName;     // unique blob identifier in Azure
    private String blobUrl;      // public/private URL to access

    private Long fileSize;       // optional
    private String fileType;     // e.g. application/pdf, image/png

    private LocalDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id", nullable = false)
    private Claim claim;

    public Document(String fileName, String blobName, String blobUrl, Long fileSize, String fileType, Claim claim) {
        this.fileName = fileName;
        this.blobName = blobName;
        this.blobUrl = blobUrl;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.claim = claim;
        this.uploadedAt = LocalDateTime.now();
    }

    public Document() {}
    
    public Long getId() {
        return id;
    }
    public String getFileName() {
        return fileName;
    }
    public String getBlobName() {
        return blobName;
    }
    public String getBlobUrl() {
        return blobUrl;
    }
    public Long getFileSize() {
        return fileSize;
    }
    public String getFileType() {
        return fileType;
    }
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }   
    public Claim getClaim() {
        return claim;   
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setBlobName(String blobName) {
        this.blobName = blobName;
    }
    public void setBlobUrl(String blobUrl) {
        this.blobUrl = blobUrl;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
    public void setClaim(Claim claim) {
        this.claim = claim;
    }
}

