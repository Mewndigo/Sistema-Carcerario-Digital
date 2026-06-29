package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.sistema_carcerario_digital.dto.OcorrenciaRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.OcorrenciaResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.Ocorrencia;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.CondenacaoRepository;
import com.cefet.sistema_carcerario_digital.repositories.OcorrenciaRepository;
import com.cefet.sistema_carcerario_digital.repositories.TipoOcorrenciaRepository;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository repo;
    private final CondenacaoRepository condenacaoRepo;
    private final TipoOcorrenciaRepository tipoOcorrenciaRepo;

    public OcorrenciaService(
            OcorrenciaRepository repo,
            CondenacaoRepository condenacaoRepository,
            TipoOcorrenciaRepository tipoOcorrenciaRepository) {
        this.repo = repo;
        this.condenacaoRepo = condenacaoRepository;
        this.tipoOcorrenciaRepo = tipoOcorrenciaRepository;
    }

    @Transactional(readOnly = true)
    public List<OcorrenciaResponseDTO> listar() {
        return repo.findAll().stream().map(OcorrenciaResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OcorrenciaResponseDTO buscarPorId(Long id) {
        Ocorrencia entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada"));
        return new OcorrenciaResponseDTO(entity);
    }

    @Transactional
    public OcorrenciaResponseDTO inserir(OcorrenciaRequestDTO dto) {
        validar(dto);

        Ocorrencia entity = new Ocorrencia();
        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);
        return new OcorrenciaResponseDTO(entity);
    }

    @Transactional
    public OcorrenciaResponseDTO alterar(Long id, OcorrenciaRequestDTO dto) {
        Ocorrencia entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada"));

        validar(dto);
        copiarDtoParaEntidade(dto, entity);
        entity.setId(id);
        entity = repo.save(entity);
        return new OcorrenciaResponseDTO(entity);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Ocorrência não encontrada");
        }
        repo.deleteById(id);
    }

    private void validar(OcorrenciaRequestDTO dto) {
        if (!condenacaoRepo.existsById(dto.getCondenacaoId())) {
            throw new ResourceNotFoundException("Condenacao nao encontrada. Id: " + dto.getCondenacaoId());
        }

        if (!tipoOcorrenciaRepo.existsById(dto.getTipoId())) {
            throw new ResourceNotFoundException("Tipo de Ocorrencia nao encontrada. Id: " + dto.getTipoId());
        }
    }

    private void copiarDtoParaEntidade(OcorrenciaRequestDTO dto, Ocorrencia entity) {
        entity.setDataRegistro(dto.getDataRegistro());
        entity.setDescricao(dto.getDescricao());
        entity.setTipo(dto.getTipoId());
        entity.setCondenacaoId(dto.getCondenacaoId());
    }
}
