package com.example.HiringAppSpringBoot.onboarding.dto;


import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotNull;

public class DocumentUploadRequestDTO {

    @NotNull
    private MultipartFile file;

    // Optional: document type (resume, id, etc.)
    private String documentType;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}

