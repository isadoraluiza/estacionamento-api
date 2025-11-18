package com.cleberleao.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaidaVeiculoDTO {
    private String placa;
    private String modelo;
    private String marca;
    private String tipoVeiculo;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private Long horasEstacionado;
    private Double valorHora;
    private Double valorTotal;
    private String numeroVaga;
}
