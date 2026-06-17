package com.cefet.sistema_carcerario_digital.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pena")
public class Pena {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataEntrada;// (Service) inserção manual
    // LocalDate.of(ano, mes, dia)

    @Column(nullable = false)
    private LocalDateTime dataSaida; // (Service) usamos o LocalDateTime.now()

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDetento situacao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private UUID pessoa_id;// o detento

    public Pena() {
    }

    public Pena(UUID id, LocalDateTime dataSaida, StatusDetento situacao, LocalDateTime dataEntrada, UUID pessoa_id) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.situacao = situacao;
        this.pessoa_id = pessoa_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public StatusDetento getSituacao() {
        return situacao;
    }

    public void setSituacao(StatusDetento situacao) {
        this.situacao = situacao;
    }

    public UUID getPessoaId() {
        return pessoa_id;
    }

    public void setPessoaId(UUID pessoa_id) {
        this.pessoa_id = pessoa_id;
    }

}