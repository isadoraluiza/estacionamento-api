package com.cleberleao.estacionamento.dto;

import com.cleberleao.estacionamento.entity.Estacionar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEstacionarDTO {

    private Long id;
    private ResponseVeiculoDTO veiculo;
    private String numeroVaga;
    private String nomeUsuario;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private Long horasEstacionado;
    private Double valorTotal;

    public ResponseEstacionarDTO(Estacionar estacionar) {
        this.id = estacionar.getId();
        this.veiculo = new ResponseVeiculoDTO(estacionar.getVeiculo());
        this.numeroVaga = estacionar.getVaga().getNumero();
        this.nomeUsuario = estacionar.getUsuario().getNome();
        this.entrada = estacionar.getEntrada();
        this.saida = estacionar.getSaida();
        this.horasEstacionado = estacionar.getHorasEstacionado();
        this.valorTotal = estacionar.getValorTotal();
    }
}
