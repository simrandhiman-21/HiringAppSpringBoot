package com.example.service.impl;

import com.example.dto.DocumentVerificationRequest;
import com.example.entity.Candidate;
import com.example.entity.Document;
import com.example.exception.CandidateNotFoundException;
import com.example.repository.CandidateRepository;
import com.example.repository.DocumentRepository;
import com.example.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final CandidateRepository candidateRepository;
    private final DocumentRepository documentRepository;

    private static final String DOCUMENT_UPLOAD_DIR = "uploads/";

    @Override
    public void uploadDocument(Long candidateId, MultipartFile file) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with ID " + candidateId + " not found"));

        String fileName = "candidate_" + candidateId + "_" + file.getOriginalFilename();
        File uploadDir = new File(DOCUMENT_UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        File dest = new File(DOCUMENT_UPLOAD_DIR + fileName);

        try {
            file.transferTo(dest);

            Document document = documentRepository.findByCandidateId(candidateId)
                    .orElse(new Document());

            document.setCandidate(candidate);
            document.setPath(dest.getAbsolutePath());
            document.setVerified(false);

            documentRepository.save(document);
            log.info("Document uploaded for candidate ID {}", candidateId);

        } catch (IOException e) {
            log.error("Failed to upload document for candidate ID {}", candidateId, e);
            throw new RuntimeException("Could not upload file", e);
        }
    }

    @Override
    public void verifyDocument(Long candidateId, DocumentVerificationRequest request) {
        Document document = documentRepository.findByCandidateId(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Document not found for candidate ID " + candidateId));

        document.setVerified(request.isVerified());
        documentRepository.save(document);
        log.info("Document verification status updated for candidate ID {}", candidateId);
    }
}
