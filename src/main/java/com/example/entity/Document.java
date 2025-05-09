package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    @Column(nullable = false)
    private boolean verified = false;

    @OneToOne
    @JoinColumn(name = "candidate_id", nullable = false, unique = true)
    private Candidate candidate;
}
