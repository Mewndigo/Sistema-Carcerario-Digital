package com.cefet.sistema_carcerario_digital.controllers;

import java.util.List;
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
        List<TipoAtividadeResponseDTO> lista = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAtividadeResponseDTO> buscar(@PathVariable Long id) {
        TipoAtividadeResponseDTO dto = service.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<TipoAtividadeResponseDTO> inserir(@Valid @RequestBody TipoAtividadeRequestDTO dto) {
        TipoAtividadeResponseDTO criado = service.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoAtividadeResponseDTO> alterar(@PathVariable Long id,
            @Valid @RequestBody TipoAtividadeRequestDTO dto) {
        TipoAtividadeResponseDTO atualizado = service.alterar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
