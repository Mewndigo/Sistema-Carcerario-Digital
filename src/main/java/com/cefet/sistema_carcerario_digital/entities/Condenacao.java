package com.cefet.sistema_carcerario_digital.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_condenacao")
public class Condenacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String descricao; // descrevemos a(s) condenação(ões) do detento

    @Column(nullable = false)
    private LocalDateTime dataEntrada; // (Service) tem que fazer a insercao manual
    // LocalDate.of(ano, mes, dia)

    @Column(nullable = false)
    private LocalDateTime dataSaida; // (Service) usamos o LocalDateTime.now()

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDetento situacao;

    @Column(name = "pessoa_id", nullable = false)
    private Long pessoaId; // o detento

    public Condenacao() {
    }

    public Condenacao(Long id, LocalDateTime dataSaida, StatusDetento situacao, LocalDateTime dataEntrada,
            Long pessoa_id) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.situacao = situacao;
        this.pessoaId = pessoa_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

}
