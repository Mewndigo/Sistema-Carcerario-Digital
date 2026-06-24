package com.cefet.sistema_carcerario_digital.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

public class RegistroAtividadeRequestDTO extends BaseDTO {

    @NotNull(message = "O campo 'dataRegistro' é obrigatório.")
    private LocalDateTime dataRegistro;

    private String descricao;

    @NotNull(message = "O campo 'tipoId' é obrigatório.")
    private Long tipoId;

    @NotNull(message = "O campo 'condenacaoId' é obrigatório.")
    private Long condenacaoId;

    @NotNull(message = "O campo 'pessoaId' é obrigatório.")
    private Long pessoaId;

    public RegistroAtividadeRequestDTO() {
    }

    public RegistroAtividadeRequestDTO(Long id, LocalDateTime dataRegistro, String descricao, Boolean status,
            Long tipoId, Long condenacaoId, Long pessoaId) {
        super(id);
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.tipoId = tipoId;
        this.condenacaoId = condenacaoId;
        this.pessoaId = pessoaId;
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

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public Long getCondenacaoId() {
        return condenacaoId;
    }

    public void setCondenacaoId(Long condenacaoId) {
        this.condenacaoId = condenacaoId;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }
}
