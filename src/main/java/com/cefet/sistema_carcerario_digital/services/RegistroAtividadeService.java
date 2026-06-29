package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.sistema_carcerario_digital.dto.RegistroAtividadeRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.RegistroAtividadeResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.RegistroAtividade;
import com.cefet.sistema_carcerario_digital.exceptions.BusinessException;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.CondenacaoRepository;
import com.cefet.sistema_carcerario_digital.repositories.PessoaRepository;
import com.cefet.sistema_carcerario_digital.repositories.RegistroAtividadeRepository;
import com.cefet.sistema_carcerario_digital.repositories.TipoAtividadeRepository;

@Service
public class RegistroAtividadeService {

    private final RegistroAtividadeRepository repo;
    private final PessoaRepository pessoaRepo;
    private final CondenacaoRepository condenacaoRepo;
    private final TipoAtividadeRepository tipoAtividadeRepo;

    public RegistroAtividadeService(
            RegistroAtividadeRepository repo,
            PessoaRepository pessoaRepository,
            CondenacaoRepository condenacaoRepository,
            TipoAtividadeRepository tipoAtividadeRepository) {
        this.repo = repo;
        this.pessoaRepo = pessoaRepository;
        this.condenacaoRepo = condenacaoRepository;
        this.tipoAtividadeRepo = tipoAtividadeRepository;
    }

    @Transactional(readOnly = true)
    public List<RegistroAtividadeResponseDTO> listar() {
        List<RegistroAtividade> lista = repo.findAll();
        return lista.stream().map(RegistroAtividadeResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RegistroAtividadeResponseDTO buscarPorId(Long id) {
        RegistroAtividade entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de Atividade não encontrada"));
        return new RegistroAtividadeResponseDTO(entity);
    }

    @Transactional
    public RegistroAtividadeResponseDTO inserir(RegistroAtividadeRequestDTO dto) {
        validar(dto);
        validarDuplicidade(dto, null);

        RegistroAtividade entity = new RegistroAtividade();
        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);
        return new RegistroAtividadeResponseDTO(entity);
    }

    @Transactional
    public RegistroAtividadeResponseDTO alterar(Long id, RegistroAtividadeRequestDTO dto) {
        RegistroAtividade entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de Atividade não encontrado. Id: " + id));

        validar(dto);
        validarDuplicidade(dto, id);

        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);

        return new RegistroAtividadeResponseDTO(entity);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Registro de Atividade não encontrado. Id: " + id);
        }
        repo.deleteById(id);
    }

    private void validar(RegistroAtividadeRequestDTO dto) {
        if (!pessoaRepo.existsById(dto.getPessoaId())) {
            throw new ResourceNotFoundException("Pessoa não encontrada. Id: " + dto.getPessoaId());
        }

        if (!condenacaoRepo.existsById(dto.getCondenacaoId())) {
            throw new ResourceNotFoundException("Condenacao nao encontrada. Id: " + dto.getCondenacaoId());
        }

        if (!tipoAtividadeRepo.existsById(dto.getTipoId())) {
            throw new ResourceNotFoundException("Tipo de Atividade nao encontrada. Id: " + dto.getTipoId());
        }
    }

    private void validarDuplicidade(RegistroAtividadeRequestDTO dto, Long id) {
        boolean duplicado = id == null
                ? repo.existsByTipoIdAndDataRegistroAndPessoaId(
                        dto.getTipoId(), dto.getDataRegistro(), dto.getPessoaId())
                : repo.existsByTipoIdAndDataRegistroAndPessoaIdAndIdNot(
                        dto.getTipoId(), dto.getDataRegistro(), dto.getPessoaId(), id);

        if (duplicado) {
            throw new BusinessException("Registro de Atividade já cadastrado para essa pessoa, atividade e data.");
        }
    }

    private void copiarDtoParaEntidade(RegistroAtividadeRequestDTO dto, RegistroAtividade entity) {
        entity.setDataRegistro(dto.getDataRegistro());
        entity.setDescricao(dto.getDescricao());
        entity.setCondenacaoId(dto.getCondenacaoId());
        entity.setPessoaId(dto.getPessoaId());
        entity.setTipoId(dto.getTipoId());
    }
}
