package com.example.controller;

import com.example.dto.DocumentVerificationRequest;
import com.example.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/{id}/upload-document")
    public ResponseEntity<Void> uploadDocument(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        documentService.uploadDocument(id, file);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/verify-document")
    public ResponseEntity<Void> verifyDocument(@PathVariable Long id, @RequestBody DocumentVerificationRequest request) {
        documentService.verifyDocument(id, request);
        return ResponseEntity.ok().build();
    }
}
