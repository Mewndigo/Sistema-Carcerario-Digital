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

import com.cefet.sistema_carcerario_digital.dto.OcorrenciaRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.OcorrenciaResponseDTO;
import com.cefet.sistema_carcerario_digital.services.OcorrenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaService service;

    public OcorrenciaController(OcorrenciaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaResponseDTO>> listar() {
        List<OcorrenciaResponseDTO> lista = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaResponseDTO> buscar(@PathVariable Long id) {
        OcorrenciaResponseDTO dto = service.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<OcorrenciaResponseDTO> inserir(@Valid @RequestBody OcorrenciaRequestDTO dto) {
        OcorrenciaResponseDTO criado = service.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaResponseDTO> alterar(@PathVariable Long id,
            @Valid @RequestBody OcorrenciaRequestDTO dto) {
        OcorrenciaResponseDTO atualizado = service.alterar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
