package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.sistema_carcerario_digital.dto.RegistroAtividadeRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.RegistroAtividadeResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.RegistroAtividade;
import com.cefet.sistema_carcerario_digital.exceptions.DatabaseException;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.RegistroAtividadeRepository;

@Service
public class RegistroAtividadeService {

    private final RegistroAtividadeRepository repo;

    public RegistroAtividadeService(RegistroAtividadeRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<RegistroAtividadeResponseDTO> listar() {
        List<RegistroAtividade> lista = repo.findAll();
        return lista.stream().map(RegistroAtividadeResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RegistroAtividadeResponseDTO buscarPorId(UUID id) {
        RegistroAtividade entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de Atividade não encontrada"));
        return new RegistroAtividadeResponseDTO(entity);
    }

    @Transactional
    public RegistroAtividadeResponseDTO inserir(RegistroAtividadeRequestDTO dto) {
        if (repo.existsByTipo_IdAndDataRegistroAndPessoa_Id(
                dto.getTipoId(),
                dto.getDataRegistro(),
                dto.getPessoaId())) {
            throw new DatabaseException("Registro de Atividade já cadastrado para essa pessoa, atividade e data.");
        }
        RegistroAtividade entity = new RegistroAtividade();
        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);
        return new RegistroAtividadeResponseDTO(entity);
    }

    @Transactional
    public RegistroAtividadeResponseDTO alterar(UUID id, RegistroAtividadeRequestDTO dto) {
        RegistroAtividade entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de Atividade não encontrado. Id: " + id));

        // se só podemos inserir uma atividade para cada pessoa por dia !
        // se não for o caso é só retirar essa verificação
        if (repo.existsByTipo_IdAndDataRegistroAndPessoa_Id(
                dto.getTipoId(),
                dto.getDataRegistro(),
                dto.getPessoaId())) {
            throw new DatabaseException("Registro de Atividade já cadastrado para essa pessoa, atividade e data.");
        }

        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);

        dto.setId(id);
        return new RegistroAtividadeResponseDTO(entity);
    }

    @Transactional
    public void excluir(UUID id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Registro de Atividade não encontrado. . Id: " + id);
        repo.deleteById(id);
    }

    private void copiarDtoParaEntidade(RegistroAtividadeRequestDTO dto, RegistroAtividade entity) {
        entity.setDataRegistro(dto.getDataRegistro());
        entity.setDescricao(dto.getDescricao());
        entity.setPenaId(dto.getPenaId());
        entity.setPessoaId(dto.getPessoaId());
        entity.setTipoId(dto.getTipoId());
    }
}
