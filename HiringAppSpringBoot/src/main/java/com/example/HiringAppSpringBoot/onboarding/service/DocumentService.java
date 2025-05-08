package com.example.HiringAppSpringBoot.onboarding.service;

import com.example.HiringAppSpringBoot.onboarding.entity.Candidate;
import com.example.HiringAppSpringBoot.onboarding.entity.DocumentInfo;
import com.example.HiringAppSpringBoot.onboarding.exception.CandidateNotFoundException;
import com.example.HiringAppSpringBoot.onboarding.repository.CandidateRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class DocumentService {

    private final CandidateRepository repository;

    @Value("${file.upload-dir:/tmp/uploads}")
    private String uploadDir;

    public DocumentService(CandidateRepository repository) {
        this.repository = repository;
    }
    public void uploadDocument(Long id, MultipartFile file) {
        Candidate candidate = repository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));

        String fileName = "resume_" + id + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir, fileName);

        try {
            Files.createDirectories(path.getParent());
            file.transferTo(path.toFile());

            // ✅ set inside DocumentInfo
            DocumentInfo docInfo = candidate.getDocumentInfo();
            if (docInfo == null) docInfo = new DocumentInfo();
            docInfo.setResumePath(path.toString());

            candidate.setDocumentInfo(docInfo);
            repository.save(candidate);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload document", e);
        }
    }

    public void verifyDocument(Long id) {
        Candidate candidate = repository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));

        DocumentInfo docInfo = candidate.getDocumentInfo();
        if (docInfo == null) docInfo = new DocumentInfo();
        docInfo.setDocumentVerified(true);

        candidate.setDocumentInfo(docInfo);
        repository.save(candidate);
    }





}
