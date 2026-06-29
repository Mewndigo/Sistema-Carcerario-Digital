package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.sistema_carcerario_digital.dto.PessoaRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.PessoaResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.Pessoa;
import com.cefet.sistema_carcerario_digital.exceptions.BusinessException;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.CondenacaoRepository;
import com.cefet.sistema_carcerario_digital.repositories.PessoaRepository;

@Service
public class PessoaService {

    private final PessoaRepository repo;
    private final CondenacaoRepository condenacaoRepo;

    PessoaService(PessoaRepository pessoaRepository, CondenacaoRepository condenacaoRepository) {
        this.repo = pessoaRepository;
        this.condenacaoRepo = condenacaoRepository;
    }

    @Transactional(readOnly = true)
    public List<PessoaResponseDTO> listar() {
        List<Pessoa> lista = repo.findAll();
        return lista.stream().map(PessoaResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PessoaResponseDTO buscarPorId(Long id) {
        Pessoa entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. Id: " + id));

        return new PessoaResponseDTO(entity);
    }

    @Transactional
    public PessoaResponseDTO inserir(PessoaRequestDTO dto) {
        String cpf = normalizarCpf(dto.getCpf());
        validarCpf(cpf);

        if (repo.existsByCpf(cpf)) {
            throw new BusinessException("CPF já cadastrado.");
        }

        Pessoa entity = new Pessoa();
        copiarDtoParaEntidade(dto, entity, cpf);

        entity = repo.save(entity);

        return new PessoaResponseDTO(entity);
    }

    @Transactional
    public PessoaResponseDTO alterar(Long id, PessoaRequestDTO dto) {
        Pessoa entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. Id: " + id));

        String cpf = normalizarCpf(dto.getCpf());
        validarCpf(cpf);

        if (repo.existsByCpfAndIdNot(cpf, id)) {
            throw new BusinessException("CPF já cadastrado.");
        }

        copiarDtoParaEntidade(dto, entity, cpf);
        entity = repo.save(entity);

        return new PessoaResponseDTO(entity);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Pessoa não encontrada. Id: " + id);
        }

        if (condenacaoRepo.existsByPessoaId(id)) {
            throw new BusinessException("Não é possível excluir pessoa com condenações vinculadas.");
        }

        repo.deleteById(id);
    }

    private void copiarDtoParaEntidade(PessoaRequestDTO dto, Pessoa entity, String cpf) {
        entity.setNome(dto.getNome());
        entity.setCpf(cpf);
    }

    private String normalizarCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private void validarCpf(String cpf) {
        if (cpf.length() != 11) {
            throw new BusinessException("CPF deve conter 11 dígitos.");
        }
    }
}
