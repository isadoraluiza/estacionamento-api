package com.cleberleao.estacionamento.entity;

import com.cleberleao.estacionamento.dto.RequestEstacionarDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estacionar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @OneToOne
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime entrada;

    private LocalDateTime saida;

    private Double valorTotal;

    private Long horasEstacionado;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataUpdate;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataUpdate = LocalDateTime.now();
    }

    public Estacionar(RequestEstacionarDTO dto, Veiculo veiculo, Vaga vaga, Usuario usuario) {
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.usuario = usuario;
        this.entrada = LocalDateTime.now();
    }
}
