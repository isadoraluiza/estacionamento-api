package com.cleberleao.estacionamento.entity;

import com.cleberleao.estacionamento.enums.StatusVaga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVaga status;

    @OneToOne(mappedBy = "vaga")
    private Estacionar estacionar;

    public boolean isOcupada() {
        return this.status == StatusVaga.OCUPADA;
    }

    public void ocupar() {
        this.status = StatusVaga.OCUPADA;
    }

    public void liberar() {
        this.status = StatusVaga.LIVRE;
    }
}