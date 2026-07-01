package com.cefet.sistema_carcerario_digital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TipoAtividadeRequestDTO extends BaseDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 4, message = "O nome deve ter no mínimo 4 caracteres.")
    private String nome;

    public TipoAtividadeRequestDTO() {
    }

    public TipoAtividadeRequestDTO(Long id, String nome, String observacoes) {
        super(id);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
