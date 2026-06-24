package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.sistema_carcerario_digital.dto.TipoOcorrenciaRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.TipoOcorrenciaResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.TipoOcorrencia;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.TipoOcorrenciaRepository;

@Service
public class TipoOcorrenciaService {

    private final TipoOcorrenciaRepository repo;

    public TipoOcorrenciaService(TipoOcorrenciaRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<TipoOcorrenciaResponseDTO> listar() {
        List<TipoOcorrencia> lista = repo.findAll();
        return lista.stream().map(TipoOcorrenciaResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TipoOcorrenciaResponseDTO buscarPorId(Long id) {
        TipoOcorrencia entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Ocorrencia nao encontrada. Id: " + id));
        return new TipoOcorrenciaResponseDTO(entity);
    }

    @Transactional
    public TipoOcorrenciaResponseDTO inserir(TipoOcorrenciaRequestDTO dto) {
        TipoOcorrencia entity = new TipoOcorrencia();
        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);
        return new TipoOcorrenciaResponseDTO(entity);
    }

    @Transactional
    public TipoOcorrenciaResponseDTO alterar(Long id, TipoOcorrenciaRequestDTO dto) {
        TipoOcorrencia entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Ocorrencia nao encontrada. Id: " + id));
        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);
        return new TipoOcorrenciaResponseDTO(entity);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de Ocorrencia nao encontrada. Id: " + id);
        }
        repo.deleteById(id);
    }

    private void copiarDtoParaEntidade(TipoOcorrenciaRequestDTO dto, TipoOcorrencia entity) {
        entity.setNome(dto.getNome());
    }
}
