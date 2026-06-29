package com.cefet.sistema_carcerario_digital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sistema_carcerario_digital.entities.TipoAtividade;

public interface TipoAtividadeRepository extends JpaRepository<TipoAtividade, Long> {

    boolean existsByNome(String nome);

    boolean existsByNomeAndIdNot(String nome, Long id);

}
