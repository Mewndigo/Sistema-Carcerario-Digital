package com.cefet.sistema_carcerario_digital.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.cefet.sistema_carcerario_digital.entities.StatusDetento;

import jakarta.validation.constraints.NotBlank;

public class PenaRequestDTO extends BaseDTO {

    private String descricao;

    @NotBlank(message = "O campo 'dataEntrada' é obrigatório.")
    private LocalDateTime dataEntrada;
    
    @NotBlank(message = "O campo 'dataSaida' é obrigatório.")
    private LocalDateTime dataSaida;

    @NotBlank(message = "O campo 'dataEntrada' é obrigatório.")
    private StatusDetento situacao;

    @NotBlank(message = "O campo 'pessoaId' é obrigatório.")
    private UUID pessoaId;

    public PenaRequestDTO() {
    }

    public PenaRequestDTO(UUID id, String nome, LocalDate dataNasc, StatusDetento status, LocalDateTime dataEntrada,
            String matricula, String bloco, Integer cela) {
        super(id);
        this.dataEntrada = dataEntrada;
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
        return pessoaId;
    }

    public void setPessoaId(UUID pessoaId) {
        this.pessoaId = pessoaId;
    }

    
}
