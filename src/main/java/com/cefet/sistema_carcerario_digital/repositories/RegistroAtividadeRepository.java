package com.cefet.sistema_carcerario_digital.repositories;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sistema_carcerario_digital.entities.RegistroAtividade;

public interface RegistroAtividadeRepository extends JpaRepository<RegistroAtividade, Long> {

    boolean existsByTipoIdAndDataRegistroAndPessoaId(
            Long tipoId,
            LocalDateTime dataRegistro,
            Long pessoaId);

    boolean existsByTipoIdAndDataRegistroAndPessoaIdAndIdNot(
            Long tipoId,
            LocalDateTime dataRegistro,
            Long pessoaId,
            Long id);

    boolean existsByCondenacaoId(Long condenacaoId);

    boolean existsByTipoId(Long tipoId);
}
