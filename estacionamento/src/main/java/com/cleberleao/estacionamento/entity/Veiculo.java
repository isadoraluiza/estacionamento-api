package com.cleberleao.estacionamento.entity;

import com.cleberleao.estacionamento.dto.RequestVeiculoDTO;
import com.cleberleao.estacionamento.enums.TipoVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String placa;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String marca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVeiculo tipo;

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

    public Veiculo(RequestVeiculoDTO dto) {
        this.placa = dto.getPlaca().toUpperCase();
        this.modelo = dto.getModelo();
        this.marca = dto.getMarca();
        this.tipo = dto.getTipo();
    }
}