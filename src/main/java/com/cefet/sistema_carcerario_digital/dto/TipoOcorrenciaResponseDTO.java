package com.cefet.sistema_carcerario_digital.dto;

import com.cefet.sistema_carcerario_digital.entities.TipoOcorrencia;

public class TipoOcorrenciaResponseDTO {
    private String nome;

    public TipoOcorrenciaResponseDTO(TipoOcorrencia entity) {
        this.nome = entity.getNome();
    }

    public String getNome() {
        return nome;
    }
}
