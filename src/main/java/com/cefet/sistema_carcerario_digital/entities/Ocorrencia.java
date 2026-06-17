package com.cefet.sistema_carcerario_digital.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_ocorrencia")
public class Ocorrencia {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;

    // Para fazer a separação dos campos de data e hora no front:
    
    // LocalDate date = LocalDate.of(year, month, dayOfMonth);
    // LocalTime time = LocalTime.of(hour, minute);
    //  return new LocalDateTime(date, time);

    @Column(nullable = false, length = 200)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tipo_ocorrencia_id", nullable = false)
    private TipoOcorrencia tipo;

    // deixamos o agente como atributo ou só o detento?
    // @ManyToOne
    // @JoinColumn(name = "pessoa_id", nullable = false)
    // private Pessoa pessoa; 

    @ManyToOne
    @JoinColumn(name = "pena_id", nullable = false)
    private Pena pena;

    // QUAL DOS DOIS FICA, OU PRECISAMOS DOS DOIS CONSTRUTURES?
    public Ocorrencia(UUID id, LocalDateTime dataRegistro, String descricao, TipoOcorrencia tipo,
            Pessoa pessoa, Pena pena) {
        this.id = id;
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.tipo = tipo;
        this.pena = pena;
        // this.pessoa = pessoa;
    }

    public Ocorrencia(UUID id, LocalDateTime dataRegistro, String descricao, TipoOcorrencia tipo, Pessoa usuario) {
        this.id = id;
        this.dataRegistro = dataRegistro;
        this.descricao = descricao;
        this.tipo = tipo;
        // this.pessoa = usuario;
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

    public TipoOcorrencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoOcorrencia tipo) {
        this.tipo = tipo;
    }

    public Pena getPena() {
        return pena;
    }

    public void setPena(Pena pena) {
        this.pena = pena;
    }
    
    // public Pessoa getPessoa() {
    //     return pessoa;
    // }

    // public void setPessoa(Pessoa pessoa) {
    //     this.pessoa = pessoa;
    // }
}
