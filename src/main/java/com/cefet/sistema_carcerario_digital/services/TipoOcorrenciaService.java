package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cefet.sistema_carcerario_digital.entities.TipoOcorrencia;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.TipoOcorrenciaRepository;

@Service
public class TipoOcorrenciaService {

    private final TipoOcorrenciaRepository repo;

    public TipoOcorrenciaService(TipoOcorrenciaRepository repo) {
        this.repo = repo;
    }

    public List<TipoOcorrencia> findAll() { return repo.findAll(); }

    public TipoOcorrencia findById(UUID id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tipo de Ocorrência não encontrada")); }

    public TipoOcorrencia create(TipoOcorrencia t) { return repo.save(t); }

    public TipoOcorrencia update(UUID id, TipoOcorrencia t) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Tipo de Ocorrência não encontrada");
        t.setId(id);
        return repo.save(t);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Tipo de Ocorrência não encontrada");
        repo.deleteById(id);
    }
}
