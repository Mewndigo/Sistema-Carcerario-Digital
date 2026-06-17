package com.cefet.sistema_carcerario_digital.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_registro_atividade")
public class RegistroAtividade {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime dataRegistro; // LocalDateTime pega a data e a hora
    // LocalDateTime.of(ano, mes, dia, hora, min)

    @Column(nullable = false, length = 200)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tipo_atividade_id", nullable = false)
    private TipoAtividade tipo;

    @ManyToOne
    @JoinColumn(name = "pena_id", nullable = false)
    private Pena pena;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    public RegistroAtividade() {
    }

    public RegistroAtividade(UUID id, LocalDateTime dataRegistro, String descricao, TipoAtividade tipo, Pena pena,
            Pessoa pessoa) {
        this.id = id;
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.tipo = tipo;
        this.pena = pena;
        this.pessoa = pessoa;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public TipoAtividade getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtividade tipo) {
        this.tipo = tipo;
    }

    public Pena getPena() {
        return pena;
    }

    public void setPena(Pena pena) {
        this.pena = pena;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
