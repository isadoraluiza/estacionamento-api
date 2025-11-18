package com.cleberleao.estacionamento.repository;

import com.cleberleao.estacionamento.entity.Estacionar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstacionarRepository extends JpaRepository<Estacionar, Long> {
    Optional<Estacionar> findByVeiculoPlacaAndSaidaIsNull(String placa);
    Long countBySaidaIsNull();
}