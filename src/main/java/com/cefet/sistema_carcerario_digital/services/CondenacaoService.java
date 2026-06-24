package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.sistema_carcerario_digital.dto.CondenacaoRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.CondenacaoResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.Condenacao;
import com.cefet.sistema_carcerario_digital.exceptions.DatabaseException;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.CondenacaoRepository;

@Service
public class CondenacaoService {

    private final CondenacaoRepository repo;

    CondenacaoService(CondenacaoRepository condenacaoRepository) {
        this.repo = condenacaoRepository;
    }

    @Transactional(readOnly = true)
    public List<CondenacaoResponseDTO> listar() {
        List<Condenacao> lista = repo.findAll();
        return lista.stream().map(CondenacaoResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CondenacaoResponseDTO buscarPorId(Long id) {
        Condenacao entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condenacao nao encontrada. Id: " + id));

        return new CondenacaoResponseDTO(entity);
    }

    @Transactional
    public CondenacaoResponseDTO inserir(CondenacaoRequestDTO dto) {

        // Temos a questao do detento poder cumprir mais de uma condenacao de uma vez,
        // o que seria uma duplicacao em tese da nossa condenacao.

        // Adicionamos mais um elemento em Condenacao para diferenciar as condenacoes
        // ou deixamos sem essa conferencia?

        // deixei isso mas estaria errado dentro desse contexto:
        // ----
        if (repo.existsByDataEntrada(dto.getDataEntrada())

                && repo.existsByPessoaId(dto.getPessoaId())) {
            throw new DatabaseException("Condenacao ja cadastrada.");
        }
        // ----

        Condenacao entity = new Condenacao();
        copiarDtoParaEntidade(dto, entity);

        entity = repo.save(entity);

        return new CondenacaoResponseDTO(entity);
    }

    @Transactional
    public CondenacaoResponseDTO alterar(Long id, CondenacaoRequestDTO dto) {
        Condenacao entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condenacao nao encontrada. Id: " + id));

        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);

        // se tiver alguma regra que estamos esquecendo tem que colocar aqui depois !

        return new CondenacaoResponseDTO(entity);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Condenacao nao encontrada. Id: " + id);
        }
        repo.deleteById(id);
    }

    private void copiarDtoParaEntidade(CondenacaoRequestDTO dto, Condenacao entity) {
        entity.setDataEntrada(dto.getDataEntrada());
        entity.setDataSaida(dto.getDataSaida());
        entity.setDescricao(dto.getDescricao());
        entity.setSituacao(dto.getSituacao());
        entity.setPessoaId(dto.getPessoaId());
    }
}
