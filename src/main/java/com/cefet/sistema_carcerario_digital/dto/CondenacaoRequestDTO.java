package com.cefet.sistema_carcerario_digital.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.cefet.sistema_carcerario_digital.entities.StatusDetento;

import jakarta.validation.constraints.NotNull;

public class CondenacaoRequestDTO extends BaseDTO {

    private String descricao;

    @NotNull(message = "O campo 'dataEntrada' e obrigatorio.")
    private LocalDateTime dataEntrada;

    @NotNull(message = "O campo 'dataSaida' e obrigatorio.")
    private LocalDateTime dataSaida;

    @NotNull(message = "O campo 'situacao' e obrigatorio.")
    private StatusDetento situacao;

    @NotNull(message = "O campo 'pessoaId' e obrigatorio.")
    private Long pessoaId;

    public CondenacaoRequestDTO() {
    }

    public CondenacaoRequestDTO(Long id, String nome, LocalDate dataNasc, StatusDetento status,
            LocalDateTime dataEntrada, String matricula, String bloco, Integer cela) {
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

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

}
