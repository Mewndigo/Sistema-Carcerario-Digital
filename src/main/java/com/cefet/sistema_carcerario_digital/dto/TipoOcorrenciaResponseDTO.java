package com.cefet.sistema_carcerario_digital.dto;

import com.cefet.sistema_carcerario_digital.entities.TipoOcorrencia;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "nome" })
public class TipoOcorrenciaResponseDTO {
    private String nome;

    public TipoOcorrenciaResponseDTO(TipoOcorrencia entity) {
        this.nome = entity.getNome();
    }

    public String getNome() {
        return nome;
    }
}
