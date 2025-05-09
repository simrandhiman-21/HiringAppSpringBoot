package com.example.service;

import com.example.dto.DocumentVerificationRequest;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    void uploadDocument(Long candidateId, MultipartFile file);
    void verifyDocument(Long candidateId, DocumentVerificationRequest request);
}
