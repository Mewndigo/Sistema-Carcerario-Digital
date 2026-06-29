package com.cefet.sistema_carcerario_digital.dto;

import com.cefet.sistema_carcerario_digital.entities.TipoAtividade;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "nome" })
public class TipoAtividadeResponseDTO {
    private String nome;

    public TipoAtividadeResponseDTO(TipoAtividade entity) {
        this.nome = entity.getNome();
    }

    public String getNome() {
        return nome;
    }
}
