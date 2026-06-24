package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.sistema_carcerario_digital.dto.OcorrenciaRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.OcorrenciaResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.Ocorrencia;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.OcorrenciaRepository;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository repo;

    public OcorrenciaService(OcorrenciaRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<OcorrenciaResponseDTO> listar() {
        return repo.findAll().stream().map(OcorrenciaResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OcorrenciaResponseDTO buscarPorId(UUID id) {
        Ocorrencia entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada"));
        return new OcorrenciaResponseDTO(entity);
    }

    @Transactional
    public OcorrenciaResponseDTO inserir(OcorrenciaRequestDTO dto) {
        Ocorrencia entity = new Ocorrencia();
        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);
        return new OcorrenciaResponseDTO(entity);
    }

    @Transactional
    public OcorrenciaResponseDTO alterar(UUID id, OcorrenciaRequestDTO dto) {
        Ocorrencia entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada"));
        copiarDtoParaEntidade(dto, entity);
        entity.setId(id);
        entity = repo.save(entity);
        return new OcorrenciaResponseDTO(entity);
    }

    @Transactional
    public void excluir(UUID id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Ocorrência não encontrada");
        }
        repo.deleteById(id);
    }

    private void copiarDtoParaEntidade(OcorrenciaRequestDTO dto, Ocorrencia entity) {
        entity.setDataRegistro(dto.getDataRegistro());
        entity.setDescricao(dto.getDescricao());
        entity.setTipo(dto.getTipoId());
        entity.setCondenacaoId(dto.getCondenacaoId());
    }
}
