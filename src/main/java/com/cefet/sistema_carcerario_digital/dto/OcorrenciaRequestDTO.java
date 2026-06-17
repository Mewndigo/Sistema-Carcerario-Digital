package com.cefet.sistema_carcerario_digital.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class OcorrenciaRequestDTO extends BaseDTO {

    @NotBlank(message = "O campo 'dataRegistro' é obrigatório.")
    private LocalDateTime dataRegistro;

    private String descricao;

    @NotBlank(message = "O campo 'tipoId' é obrigatório.")
    private UUID tipoId;

    @NotBlank(message = "O campo 'penaId' é obrigatório.")
    private UUID penaId;

    public OcorrenciaRequestDTO() {
    }

    public OcorrenciaRequestDTO(UUID id, LocalDateTime dataRegistro, String descricao, UUID tipoId, UUID penaId) {
        super(id);
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.tipoId = tipoId;
        this.penaId = penaId;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UUID getTipoId() {
        return tipoId;
    }

    public void setTipoId(UUID tipoId) {
        this.tipoId = tipoId;
    }

    public UUID getPenaId() {
        return penaId;
    }

    public void setPenaId(UUID penaId) {
        this.penaId = penaId;
    }
}
