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

    private final PenaRepository penaRepository;

    PenaService(PenaRepository penaRepository) {
        this.penaRepository = penaRepository;
    }

    @Transactional(readOnly = true)
    public List<PenaResponseDTO> listar() {
        List<Pena> lista = penaRepository.findAll();
        return lista.stream().map(PenaResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PenaResponseDTO buscarPorId(UUID id) {
        Pena entity = penaRepository.findById(id)
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
        if (penaRepository.existsByDataEntrada(dto.getDataEntrada())

                && penaRepository.existsByPessoa(dto.getPessoaId())) {
            throw new DatabaseException("Pena já cadastrado.");
        }
        // ----

        Pena entity = new Pena();
        copiarDtoParaEntidade(dto, entity);

        entity = penaRepository.save(entity);

        return new PenaResponseDTO(entity);
    }

    // ----> PAREI AQUI <----

    @Transactional
    public PenaResponseDTO alterar(UUID id, PenaRequestDTO dto) {
        Pena entity = penaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pena não encontrado. Id: " + id));

        copiarDtoParaEntidade(dto, entity);
        entity = penaRepository.save(entity);

        return new PenaResponseDTO(entity);
    }

    @Transactional
    public void excluir(UUID id) {
        if (!penaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pena não encontrado. Id: " + id);
        }
        penaRepository.deleteById(id);
    }

// > FAZER UM COMMIT DEPOIS QUE TERMINAR AQUI, com o nome "commit de PessoaService"

    private void copiarDtoParaEntidade(PenaRequestDTO dto, Pena entity) {
        entity.setDataEntrada(null);
        entity.setDataSaida(null);
        entity.setDescricao(null);
        entity.setPessoa(null);
        entity.setSituacao(null);
    }
}
