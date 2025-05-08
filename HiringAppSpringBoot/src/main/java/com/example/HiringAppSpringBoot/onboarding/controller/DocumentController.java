package com.example.HiringAppSpringBoot.onboarding.controller;

import com.example.HiringAppSpringBoot.onboarding.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/candidates/{id}")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload-document")
    public ResponseEntity<?> uploadDocument(@PathVariable Long id,
                                            @RequestParam("file") MultipartFile file) {
        documentService.uploadDocument(id, file);
        return ResponseEntity.ok("Document uploaded successfully");
    }

    @PutMapping("/verify-document")
    public ResponseEntity<?> verifyDocument(@PathVariable Long id) {
        documentService.verifyDocument(id);
        return ResponseEntity.ok("Document verified");
    }
}

