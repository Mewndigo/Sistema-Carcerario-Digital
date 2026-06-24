package com.cefet.sistema_carcerario_digital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sistema_carcerario_digital.entities.Ocorrencia;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

}
