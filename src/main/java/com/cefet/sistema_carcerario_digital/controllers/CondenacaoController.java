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

import com.cefet.sistema_carcerario_digital.dto.CondenacaoRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.CondenacaoResponseDTO;
import com.cefet.sistema_carcerario_digital.services.CondenacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/condenacoes")
public class CondenacaoController {

    private final CondenacaoService service;

    public CondenacaoController(CondenacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CondenacaoResponseDTO>> listar() {
        List<CondenacaoResponseDTO> lista = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondenacaoResponseDTO> buscar(@PathVariable Long id) {
        CondenacaoResponseDTO dto = service.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<CondenacaoResponseDTO> inserir(@Valid @RequestBody CondenacaoRequestDTO dto) {
        CondenacaoResponseDTO criado = service.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondenacaoResponseDTO> alterar(@PathVariable Long id,
            @Valid @RequestBody CondenacaoRequestDTO dto) {
        CondenacaoResponseDTO atualizado = service.alterar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
