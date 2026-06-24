package com.cefet.sistema_carcerario_digital.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.sistema_carcerario_digital.dto.TipoAtividadeRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.TipoAtividadeResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.TipoAtividade;
import com.cefet.sistema_carcerario_digital.services.TipoAtividadeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipos-atividade")
public class TipoAtividadeController {

    private final TipoAtividadeService service;

    public TipoAtividadeController(TipoAtividadeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoAtividadeResponseDTO>> listar() {
        List<TipoAtividadeResponseDTO> lista = service.findAll().stream().map(TipoAtividadeResponseDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAtividadeResponseDTO> buscar(@PathVariable UUID id) {
        TipoAtividade entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new TipoAtividadeResponseDTO(entity));
    }

    @PostMapping
    public ResponseEntity<TipoAtividadeResponseDTO> inserir(@Valid @RequestBody TipoAtividadeRequestDTO dto) {
        TipoAtividade entity = new TipoAtividade();
        entity.setNome(dto.getNome());

        TipoAtividade criado = service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TipoAtividadeResponseDTO(criado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoAtividadeResponseDTO> alterar(@PathVariable UUID id,
            @Valid @RequestBody TipoAtividadeRequestDTO dto) {
        TipoAtividade entity = new TipoAtividade();
        entity.setNome(dto.getNome());

        TipoAtividade atualizado = service.update(id, entity);
        return ResponseEntity.status(HttpStatus.OK).body(new TipoAtividadeResponseDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
