package com.cefet.sistema_carcerario_digital.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.cefet.sistema_carcerario_digital.entities.Ocorrencia;

public class OcorrenciaResponseDTO {
    private LocalDateTime dataRegistro;

    private String descricao;
    private UUID tipoId;
    private UUID penaId;

    public OcorrenciaResponseDTO(Ocorrencia entity) {
        this.dataRegistro = entity.getDataRegistro();
        this.descricao = entity.getDescricao();
        this.penaId = entity.getPenaId();
        this.tipoId = entity.getTipoId();
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public String getDescricao() {
        return descricao;
    }

    public UUID getPenaId() {
        return penaId;
    }

    public UUID getTipoId() {
        return tipoId;
    }
}
