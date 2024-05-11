 package com.uvas.uvasapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.uvas.uvasapi.domain.enums.PhoneType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "phone")
@Data
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneType PhoneType;

    @Column(length = 20)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference
    private Pessoa pessoaId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
