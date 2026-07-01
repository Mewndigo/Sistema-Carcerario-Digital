package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.sistema_carcerario_digital.dto.TipoAtividadeRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.TipoAtividadeResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.TipoAtividade;
import com.cefet.sistema_carcerario_digital.exceptions.BusinessException;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.RegistroAtividadeRepository;
import com.cefet.sistema_carcerario_digital.repositories.TipoAtividadeRepository;

@Service
public class TipoAtividadeService {

    private final TipoAtividadeRepository repo;
    private final RegistroAtividadeRepository registroAtividadeRepo;

    public TipoAtividadeService(
            TipoAtividadeRepository repo,
            RegistroAtividadeRepository registroAtividadeRepository) {
        this.repo = repo;
        this.registroAtividadeRepo = registroAtividadeRepository;
    }

    @Transactional(readOnly = true)
    public List<TipoAtividadeResponseDTO> listar() {
        List<TipoAtividade> lista = repo.findAll();
        return lista.stream().map(TipoAtividadeResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TipoAtividadeResponseDTO buscarPorId(Long id) {
        TipoAtividade entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Atividade nao encontrada. Id: " + id));
        return new TipoAtividadeResponseDTO(entity);
    }

    @Transactional
    public TipoAtividadeResponseDTO inserir(TipoAtividadeRequestDTO dto) {
        if (repo.existsByNome(dto.getNome())) {
            throw new BusinessException("Tipo de Atividade já cadastrado.");
        }

        TipoAtividade entity = new TipoAtividade();
        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);
        return new TipoAtividadeResponseDTO(entity);
    }

    @Transactional
    public TipoAtividadeResponseDTO alterar(Long id, TipoAtividadeRequestDTO dto) {
        TipoAtividade entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Atividade nao encontrada. Id: " + id));

        if (repo.existsByNomeAndIdNot(dto.getNome(), id)) {
            throw new BusinessException("Tipo de Atividade já cadastrado.");
        }

        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);
        return new TipoAtividadeResponseDTO(entity);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de Atividade nao encontrada. Id: " + id);
        }

        if (registroAtividadeRepo.existsByTipoId(id)) {
            throw new BusinessException("Não é possível excluir tipo de atividade com registros vinculados.");
        }

        repo.deleteById(id);
    }

    private void copiarDtoParaEntidade(TipoAtividadeRequestDTO dto, TipoAtividade entity) {
        entity.setNome(dto.getNome());
    }
}
