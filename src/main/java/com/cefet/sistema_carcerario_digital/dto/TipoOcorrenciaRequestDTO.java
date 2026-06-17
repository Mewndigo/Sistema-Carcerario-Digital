package com.cefet.sistema_carcerario_digital.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TipoOcorrenciaRequestDTO extends BaseDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 4, message = "O nome deve ter no mínimo 4 caracteres.")
    private String nome;

    public TipoOcorrenciaRequestDTO() {
    }

    public TipoOcorrenciaRequestDTO(UUID id, String nome) {
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
