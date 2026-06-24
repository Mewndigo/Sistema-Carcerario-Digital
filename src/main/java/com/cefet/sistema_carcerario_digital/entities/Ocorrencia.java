package com.cefet.sistema_carcerario_digital.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_ocorrencia")
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(name = "tipo_ocorrencia_id", nullable = false)
    private Long tipoId;

    @Column(name = "condenacao_id", nullable = false)
    private Long condenacaoId;

    public Ocorrencia() {
    }

    public Ocorrencia(Long id, LocalDateTime dataRegistro, String descricao, Long tipo_id, Long condenacao_id) {
        this.id = id;
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.tipoId = tipo_id;
        this.condenacaoId = condenacao_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setTipo(Long tipoId) {
        this.tipoId = tipoId;
    }

    public Long getCondenacaoId() {
        return condenacaoId;
    }

    public void setCondenacaoId(Long condenacaoId) {
        this.condenacaoId = condenacaoId;
    }
}
