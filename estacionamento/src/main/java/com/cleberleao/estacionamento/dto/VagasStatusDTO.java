package com.cleberleao.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VagasStatusDTO {
    private Long vagasOcupadas;
    private Long vagasLivres;
    private Long totalVagas;
    private Long totalCarrosEstacionados;
}