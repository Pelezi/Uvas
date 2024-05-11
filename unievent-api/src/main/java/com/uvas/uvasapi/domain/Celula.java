package com.uvas.uvasapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "celulas")
@Data
public class Celula {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(length = 200, nullable = false)
    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inauguracao")
    private LocalDateTime dataInauguracao;
    
    @ManyToOne
    @JoinColumn(name = "discipulador_id")
    @JsonBackReference
    private Discipulador discipuladorId;
    
    @ManyToOne
    @JoinColumn(name = "lider_id")
    @JsonBackReference
    private Lider liderId;

    @OneToMany(mappedBy = "celulaId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Pessoa> pessoas;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id")
    private Endereco enderecoId;

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