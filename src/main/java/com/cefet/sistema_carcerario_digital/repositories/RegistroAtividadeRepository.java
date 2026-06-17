package com.cefet.sistema_carcerario_digital.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sistema_carcerario_digital.entities.RegistroAtividade;

public interface RegistroAtividadeRepository extends JpaRepository<RegistroAtividade, UUID> {

    boolean existsByTipo_IdAndDataRegistroAndPessoa_Id(
            UUID tipoId,
            LocalDateTime dataRegistro,
            UUID pessoaId);
}
