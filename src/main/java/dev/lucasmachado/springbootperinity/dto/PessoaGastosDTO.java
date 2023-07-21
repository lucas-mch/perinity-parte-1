package dev.lucasmachado.springbootperinity.dto;

import java.io.Serializable;

public class PessoaGastosDTO implements Serializable {
    private Long id;
    private String nome;
    private double mediaHorasGastas;

    public PessoaGastosDTO(Long id, String nome, double mediaHorasGastas) {
        this.id = id;
        this.nome = nome;
        this.mediaHorasGastas = mediaHorasGastas;
    }

    public String getNome() {
        return nome;
    }

    public double getMediaHorasGastas() {
        return mediaHorasGastas;
    }

    public Long getId() {
        return id;
    }
}
