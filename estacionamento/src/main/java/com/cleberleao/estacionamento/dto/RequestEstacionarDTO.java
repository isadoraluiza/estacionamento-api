package com.cleberleao.estacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEstacionarDTO {
    private String placaVeiculo;
    private Long vagaId;
    private Long usuarioId;
}