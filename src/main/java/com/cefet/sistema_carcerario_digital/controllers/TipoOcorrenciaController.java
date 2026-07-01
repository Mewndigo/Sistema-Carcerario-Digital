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

import com.cefet.sistema_carcerario_digital.dto.TipoOcorrenciaRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.TipoOcorrenciaResponseDTO;
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
        List<TipoOcorrenciaResponseDTO> lista = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoOcorrenciaResponseDTO> buscar(@PathVariable Long id) {
        TipoOcorrenciaResponseDTO dto = service.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<TipoOcorrenciaResponseDTO> inserir(@Valid @RequestBody TipoOcorrenciaRequestDTO dto) {
        TipoOcorrenciaResponseDTO criado = service.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoOcorrenciaResponseDTO> alterar(@PathVariable Long id,
            @Valid @RequestBody TipoOcorrenciaRequestDTO dto) {
        TipoOcorrenciaResponseDTO atualizado = service.alterar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
