package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cefet.sistema_carcerario_digital.entities.Ocorrencia;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.OcorrenciaRepository;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository repo;

    public OcorrenciaService(OcorrenciaRepository repo) {
        this.repo = repo;
    }

    public List<Ocorrencia> findAll() { return repo.findAll(); }

    public Ocorrencia findById(UUID id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada")); }

    public Ocorrencia create(Ocorrencia o) { return repo.save(o); }

    public Ocorrencia update(UUID id, Ocorrencia o) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Ocorrência não encontrada");
        o.setId(id);
        return repo.save(o);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Ocorrência não encontrada");
        repo.deleteById(id);
    }
}
