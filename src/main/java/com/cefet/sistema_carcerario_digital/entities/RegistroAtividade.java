package com.cefet.sistema_carcerario_digital.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_registro_atividade")
public class RegistroAtividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataRegistro; 

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(name = "tipo_atividade_id", nullable = false)
    private Long tipoId;

    @Column(name = "condenacao_id", nullable = false)
    private Long condenacaoId;// o detento

    @Column(name = "pessoa_id", nullable = false)
    private Long pessoaId;// agente que registrou a condenacao

    public RegistroAtividade() {
    }

    public RegistroAtividade(Long id, LocalDateTime dataRegistro, String descricao, Long tipo_id, Long condenacao_id,
            Long pessoa_id) {
        this.id = id;
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.tipoId = tipo_id;
        this.condenacaoId = condenacao_id;
        this.pessoaId = pessoa_id;
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
