package com.cleberleao.estacionamento.servirce;

import com.cleberleao.estacionamento.dto.*;
import com.cleberleao.estacionamento.entity.Estacionar;
import com.cleberleao.estacionamento.entity.Usuario;
import com.cleberleao.estacionamento.entity.Vaga;
import com.cleberleao.estacionamento.entity.Veiculo;
import com.cleberleao.estacionamento.enums.StatusVaga;
import com.cleberleao.estacionamento.exception.VagaOcupadaException;
import com.cleberleao.estacionamento.exception.VeiculoNaoEncontradoException;
import com.cleberleao.estacionamento.repository.EstacionarRepository;
import com.cleberleao.estacionamento.repository.UsuarioRepository;
import com.cleberleao.estacionamento.repository.VagaRepository;
import com.cleberleao.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstacionarService {

    @Autowired
    private EstacionarRepository estacionarRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public ResponseEstacionarDTO cadastrar(RequestEstacionarDTO dto) {
        // Buscar o veículo pela placa
        Veiculo veiculo = veiculoRepository.findByPlaca(dto.getPlacaVeiculo())
                .orElseThrow(() -> new VeiculoNaoEncontradoException(
                        "Veículo com placa " + dto.getPlacaVeiculo() + " não encontrado"));

        // Buscar a vaga
        Vaga vaga = vagaRepository.findById(dto.getVagaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Vaga ID " + dto.getVagaId() + " não encontrada"));

        // Verificar se a vaga está ocupada
        if (vaga.isOcupada()) {
            throw new VagaOcupadaException(
                    "A vaga " + vaga.getNumero() + " já está ocupada. Escolha outra vaga.");
        }

        // Buscar o usuário
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuário ID " + dto.getUsuarioId() + " não encontrado"));

        // Criar o registro de estacionamento
        Estacionar estacionar = new Estacionar(dto, veiculo, vaga, usuario);

        // Ocupar a vaga
        vaga.ocupar();
        vagaRepository.save(vaga);

        // Salvar o estacionamento
        Estacionar saved = estacionarRepository.save(estacionar);

        return new ResponseEstacionarDTO(saved);
    }

    public VagasStatusDTO buscarVagasLivres() {
        Long vagasOcupadas = vagaRepository.countByStatus(StatusVaga.OCUPADA);
        Long vagasLivres = vagaRepository.countByStatus(StatusVaga.LIVRE);
        Long totalVagas = vagaRepository.count();
        Long totalCarros = estacionarRepository.countBySaidaIsNull();

        return new VagasStatusDTO(vagasOcupadas, vagasLivres, totalVagas, totalCarros);
    }

    public List<ResponseEstacionarDTO> buscarVagasOcupadas() {
        List<Vaga> vagasOcupadas = vagaRepository.findByStatus(StatusVaga.OCUPADA);

        return vagasOcupadas.stream()
                .map(vaga -> {
                    Estacionar estacionar = vaga.getEstacionar();
                    return new ResponseEstacionarDTO(estacionar);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public SaidaVeiculoDTO registrarSaida(String placa) {
        // Buscar o registro de estacionamento ativo (sem saída)
        Estacionar estacionar = estacionarRepository.findByVeiculoPlacaAndSaidaIsNull(placa)
                .orElseThrow(() -> new VeiculoNaoEncontradoException(
                        "Veículo com placa " + placa + " não está estacionado no momento"));

        // Registrar horário de saída
        LocalDateTime saida = LocalDateTime.now();
        estacionar.setSaida(saida);

        // Calcular horas estacionado (arredondar para cima)
        Duration duration = Duration.between(estacionar.getEntrada(), saida);
        long minutos = duration.toMinutes();
        long horas = (minutos + 59) / 60; // Arredondar para cima
        estacionar.setHorasEstacionado(horas);

        // Calcular valor total
        double valorHora = estacionar.getVeiculo().getTipo().getValorHora();
        double valorTotal = horas * valorHora;
        estacionar.setValorTotal(valorTotal);

        // Salvar o estacionamento atualizado
        estacionarRepository.save(estacionar);

        // Liberar a vaga
        Vaga vaga = estacionar.getVaga();
        vaga.liberar();
        vagaRepository.save(vaga);

        // Criar DTO de resposta
        SaidaVeiculoDTO saidaDTO = new SaidaVeiculoDTO();
        saidaDTO.setPlaca(estacionar.getVeiculo().getPlaca());
        saidaDTO.setModelo(estacionar.getVeiculo().getModelo());
        saidaDTO.setMarca(estacionar.getVeiculo().getMarca());
        saidaDTO.setTipoVeiculo(estacionar.getVeiculo().getTipo().name());
        saidaDTO.setEntrada(estacionar.getEntrada());
        saidaDTO.setSaida(saida);
        saidaDTO.setHorasEstacionado(horas);
        saidaDTO.setValorHora(valorHora);
        saidaDTO.setValorTotal(valorTotal);
        saidaDTO.setNumeroVaga(vaga.getNumero());

        return saidaDTO;
    }
}