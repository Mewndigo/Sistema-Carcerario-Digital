package com.cefet.sistema_carcerario_digital.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cefet.sistema_carcerario_digital.entities.RegistroAtividade;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.RegistroAtividadeRepository;

@Service
public class RegistroAtividadeService {

    private final RegistroAtividadeRepository repo;

    public RegistroAtividadeService(RegistroAtividadeRepository repo) {
        this.repo = repo;
    }

    public List<RegistroAtividade> findAll() { return repo.findAll(); }

    public RegistroAtividade findById(UUID id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro de Atividade não encontrada")); }

    public RegistroAtividade create(RegistroAtividade r) { return repo.save(r); }

    public RegistroAtividade update(UUID id, RegistroAtividade r) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Registro de Atividade não encontrada");
        r.setId(id);
        return repo.save(r);
    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) throw new ResourceNotFoundException("Registro de Atividade não encontrada");
        repo.deleteById(id);
    }
}
