package com.cefet.sistema_carcerario_digital.dto;

import java.time.LocalDateTime;
import com.cefet.sistema_carcerario_digital.entities.Ocorrencia;

public class OcorrenciaResponseDTO {
    private LocalDateTime dataRegistro;

    private String descricao;
    private Long tipoId;
    private Long condenacaoId;

    public OcorrenciaResponseDTO(Ocorrencia entity) {
        this.dataRegistro = entity.getDataRegistro();
        this.descricao = entity.getDescricao();
        this.condenacaoId = entity.getCondenacaoId();
        this.tipoId = entity.getTipoId();
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCondenacaoId() {
        return condenacaoId;
    }

    public Long getTipoId() {
        return tipoId;
    }
}
