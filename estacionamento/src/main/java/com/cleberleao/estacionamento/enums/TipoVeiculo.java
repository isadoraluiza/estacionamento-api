package com.cleberleao.estacionamento.enums;

public enum TipoVeiculo {
    PEQUENO(16.00),
    GRANDE(25.00),
    MOTO(8.00);

    private final double valorHora;

    TipoVeiculo(double valorHora) {
        this.valorHora = valorHora;
    }

    public double getValorHora() {
        return valorHora;
    }
}