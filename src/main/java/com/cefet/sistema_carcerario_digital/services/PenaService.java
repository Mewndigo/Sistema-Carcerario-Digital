package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.sistema_carcerario_digital.dto.PenaResponseDTO;
import com.cefet.sistema_carcerario_digital.dto.PenaRequestDTO;
import com.cefet.sistema_carcerario_digital.entities.Pena;
import com.cefet.sistema_carcerario_digital.exceptions.DatabaseException;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.PenaRepository;

@Service
public class PenaService {

    private final PenaRepository repo;

    PenaService(PenaRepository penaRepository) {
        this.repo = penaRepository;
    }

    @Transactional(readOnly = true)
    public List<PenaResponseDTO> listar() {
        List<Pena> lista = repo.findAll();
        return lista.stream().map(PenaResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PenaResponseDTO buscarPorId(UUID id) {
        Pena entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pena não encontrado. Id: " + id));

        return new PenaResponseDTO(entity);
    }

    @Transactional
    public PenaResponseDTO inserir(PenaRequestDTO dto) {

        // Temos a questão do detento poder cumprir mais de uma pena de uma vez,
        // o que seria uma duplicação em tese da nossa pena

        // Adicionamos mais um elemento em Pena para diferenciar as penas
        // ou deixamos sem essa conferencia?

        // deixei isso mas estaria errado dentro desse contexto:
        // ----
        if (repo.existsByDataEntrada(dto.getDataEntrada())

                && repo.existsByPessoa(dto.getPessoaId())) {
            throw new DatabaseException("Pena já cadastrado.");
        }
        // ----

        Pena entity = new Pena();
        copiarDtoParaEntidade(dto, entity);

        entity = repo.save(entity);

        return new PenaResponseDTO(entity);
    }

    // ----> PAREI AQUI <----

    @Transactional
    public PenaResponseDTO alterar(UUID id, PenaRequestDTO dto) {
        Pena entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pena não encontrado. Id: " + id));

        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);

        // se tiver alguma regra que estamos esquecendo tem que colocar aqui depois !

        return new PenaResponseDTO(entity);
    }

    @Transactional
    public void excluir(UUID id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Pena não encontrado. Id: " + id);
        }
        repo.deleteById(id);
    }

    private void copiarDtoParaEntidade(PenaRequestDTO dto, Pena entity) {
        entity.setDataEntrada(dto.getDataEntrada());
        entity.setDataSaida(dto.getDataSaida());
        entity.setDescricao(dto.getDescricao());
        entity.setSituacao(dto.getSituacao());
        entity.setPessoaId(dto.getPessoaId());
    }
}
