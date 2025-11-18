package com.cleberleao.estacionamento.dto;

import com.cleberleao.estacionamento.entity.Veiculo;
import com.cleberleao.estacionamento.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVeiculoDTO {
    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    private TipoVeiculo tipo;
    private LocalDateTime dataCriacao;

    public ResponseVeiculoDTO(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.placa = veiculo.getPlaca();
        this.modelo = veiculo.getModelo();
        this.marca = veiculo.getMarca();
        this.tipo = veiculo.getTipo();
        this.dataCriacao = veiculo.getDataCriacao();
    }
}