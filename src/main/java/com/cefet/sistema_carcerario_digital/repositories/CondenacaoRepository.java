package com.cefet.sistema_carcerario_digital.repositories;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sistema_carcerario_digital.entities.Condenacao;

public interface CondenacaoRepository extends JpaRepository<Condenacao, Long> {

    boolean existsByDataEntrada(LocalDateTime dataEntrada);

    boolean existsByPessoaId(Long pessoaId);

}
