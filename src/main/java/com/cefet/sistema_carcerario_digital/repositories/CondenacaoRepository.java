package com.cefet.sistema_carcerario_digital.repositories;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sistema_carcerario_digital.entities.Condenacao;
import com.cefet.sistema_carcerario_digital.entities.StatusDetento;

public interface CondenacaoRepository extends JpaRepository<Condenacao, Long> {

    boolean existsByPessoaId(Long pessoaId);

    boolean existsByPessoaIdAndSituacaoInAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqual(
            Long pessoaId,
            Collection<StatusDetento> situacoes,
            LocalDateTime dataSaida,
            LocalDateTime dataEntrada);

    boolean existsByPessoaIdAndSituacaoInAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndIdNot(
            Long pessoaId,
            Collection<StatusDetento> situacoes,
            LocalDateTime dataSaida,
            LocalDateTime dataEntrada,
            Long id);

}
