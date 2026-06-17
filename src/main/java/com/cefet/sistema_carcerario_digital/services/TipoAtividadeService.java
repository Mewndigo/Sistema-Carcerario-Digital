package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cefet.sistema_carcerario_digital.entities.TipoAtividade;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.TipoAtividadeRepository;

@Service
public class TipoAtividadeService {

    private final TipoAtividadeRepository repo;

    public TipoAtividadeService(TipoAtividadeRepository repo) {
        this.repo = repo;
    }

    public List<TipoAtividade> findAll() { return repo.findAll(); }

    public TipoAtividade findById(UUID id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tipo de Atividade não encontrada")); }

    public TipoAtividade create(TipoAtividade t) { return repo.save(t); }

    public TipoAtividade update(UUID id, TipoAtividade t) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Tipo de Atividade não encontrada");
        t.setId(id);
        return repo.save(t);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Tipo de Atividade não encontrada");
        repo.deleteById(id);
    }
}
