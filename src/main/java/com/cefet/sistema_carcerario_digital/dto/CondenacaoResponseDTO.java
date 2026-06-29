package com.cefet.sistema_carcerario_digital.dto;

import java.time.LocalDateTime;
import com.cefet.sistema_carcerario_digital.entities.Condenacao;
import com.cefet.sistema_carcerario_digital.entities.StatusDetento;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "descricao", "dataEntrada", "dataSaida", "situacao", "pessoaId" })
public class CondenacaoResponseDTO {

    private String descricao;
    private LocalDateTime dataEntrada, dataSaida;
    private StatusDetento situacao;
    private Long pessoaId;

    public CondenacaoResponseDTO(Condenacao entity) {
        this.descricao = entity.getDescricao();
        this.dataEntrada = entity.getDataEntrada();
        this.dataSaida = entity.getDataSaida();
        this.pessoaId = entity.getPessoaId();
        this.situacao = entity.getSituacao();
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public StatusDetento getSituacao() {
        return situacao;
    }

    public Long getPessoaId() {
        return pessoaId;
    }
}
