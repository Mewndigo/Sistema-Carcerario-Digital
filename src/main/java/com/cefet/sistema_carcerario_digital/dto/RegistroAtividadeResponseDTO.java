package com.cefet.sistema_carcerario_digital.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.cefet.sistema_carcerario_digital.entities.RegistroAtividade;

public class RegistroAtividadeResponseDTO {
    private LocalDateTime dataRegistro;
    private String descricao;
    private UUID tipoId;
    private UUID penaId;
    private UUID pessoaId;

    public RegistroAtividadeResponseDTO(RegistroAtividade entity) {
        this.dataRegistro = entity.getDataRegistro();
        this.descricao = entity.getDescricao();
        this.penaId = entity.getPena().getId();
        this.tipoId = entity.getTipo().getId();
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

    public UUID getPessoaId() {
        return pessoaId;
    }

    public UUID getTipoId() {
        return tipoId;
    }
}
