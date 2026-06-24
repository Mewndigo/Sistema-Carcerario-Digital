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

import com.cefet.sistema_carcerario_digital.dto.TipoOcorrenciaRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.TipoOcorrenciaResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.TipoOcorrencia;
import com.cefet.sistema_carcerario_digital.services.TipoOcorrenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipos-ocorrencia")
public class TipoOcorrenciaController {

    private final TipoOcorrenciaService service;

    public TipoOcorrenciaController(TipoOcorrenciaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoOcorrenciaResponseDTO>> listar() {
        List<TipoOcorrenciaResponseDTO> lista = service.findAll().stream().map(TipoOcorrenciaResponseDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoOcorrenciaResponseDTO> buscar(@PathVariable UUID id) {
        TipoOcorrencia entity = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new TipoOcorrenciaResponseDTO(entity));
    }

    @PostMapping
    public ResponseEntity<TipoOcorrenciaResponseDTO> inserir(@Valid @RequestBody TipoOcorrenciaRequestDTO dto) {
        TipoOcorrencia entity = new TipoOcorrencia();
        entity.setNome(dto.getNome());

        TipoOcorrencia criado = service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TipoOcorrenciaResponseDTO(criado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoOcorrenciaResponseDTO> alterar(@PathVariable UUID id,
            @Valid @RequestBody TipoOcorrenciaRequestDTO dto) {
        TipoOcorrencia entity = new TipoOcorrencia();
        entity.setNome(dto.getNome());

        TipoOcorrencia atualizado = service.update(id, entity);
        return ResponseEntity.status(HttpStatus.OK).body(new TipoOcorrenciaResponseDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
