package com.cleberleao.estacionamento.repository;

import com.cleberleao.estacionamento.entity.Vaga;
import com.cleberleao.estacionamento.enums.StatusVaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    Long countByStatus(StatusVaga status);
    List<Vaga> findByStatus(StatusVaga status);
}