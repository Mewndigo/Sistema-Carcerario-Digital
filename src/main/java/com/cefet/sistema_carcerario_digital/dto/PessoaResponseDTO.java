package com.cefet.sistema_carcerario_digital.dto;

import java.util.UUID;

import com.cefet.sistema_carcerario_digital.entities.Pessoa;

public class PessoaResponseDTO {
    private UUID id;
    private String nome;
    private String cpf;

    public PessoaResponseDTO(Pessoa entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
    }

    public String getCpf() {
        return cpf;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
