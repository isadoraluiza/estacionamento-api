package com.cleberleao.estacionamento.controller;

import com.cleberleao.estacionamento.dto.*;
import com.cleberleao.estacionamento.servirce.EstacionarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estacionar")
@CrossOrigin("*")
@Tag(name = "Estacionamento", description = "Gerenciamento de entrada e saída de veículos")
public class EstacionarController {

    @Autowired
    private EstacionarService estacionarService;

    @PostMapping
    @Operation(summary = "Registrar entrada de veículo",
            description = "Registra a entrada de um veículo em uma vaga específica")
    public ResponseEntity<ResponseEstacionarDTO> cadastrar(@RequestBody RequestEstacionarDTO dto) {
        ResponseEstacionarDTO responseEstacionarDTO = estacionarService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseEstacionarDTO);
    }

    @GetMapping("/status-vagas")
    @Operation(summary = "Consultar status das vagas",
            description = "Retorna informações sobre vagas ocupadas, livres e total de carros")
    public ResponseEntity<VagasStatusDTO> consultarStatusVagas() {
        VagasStatusDTO status = estacionarService.buscarVagasLivres();
        return ResponseEntity.ok(status);
    }

    @GetMapping("/vagas-ocupadas")
    @Operation(summary = "Listar vagas ocupadas",
            description = "Retorna a lista de todas as vagas ocupadas com informações dos veículos")
    public ResponseEntity<List<ResponseEstacionarDTO>> listarVagasOcupadas() {
        List<ResponseEstacionarDTO> vagasOcupadas = estacionarService.buscarVagasOcupadas();
        return ResponseEntity.ok(vagasOcupadas);
    }

    @PostMapping("/saida/{placa}")
    @Operation(summary = "Registrar saída de veículo",
            description = "Registra a saída de um veículo e calcula o valor a pagar")
    public ResponseEntity<SaidaVeiculoDTO> registrarSaida(@PathVariable String placa) {
        SaidaVeiculoDTO saidaDTO = estacionarService.registrarSaida(placa.toUpperCase());
        return ResponseEntity.ok(saidaDTO);
    }
}