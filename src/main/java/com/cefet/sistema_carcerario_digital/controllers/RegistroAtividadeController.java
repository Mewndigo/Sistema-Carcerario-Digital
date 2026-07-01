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

import com.cefet.sistema_carcerario_digital.dto.RegistroAtividadeRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.RegistroAtividadeResponseDTO;
import com.cefet.sistema_carcerario_digital.services.RegistroAtividadeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/registros-atividade")
public class RegistroAtividadeController {

    private final RegistroAtividadeService service;

    public RegistroAtividadeController(RegistroAtividadeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RegistroAtividadeResponseDTO>> listar() {
        List<RegistroAtividadeResponseDTO> lista = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroAtividadeResponseDTO> buscar(@PathVariable Long id) {
        RegistroAtividadeResponseDTO dto = service.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<RegistroAtividadeResponseDTO> inserir(@Valid @RequestBody RegistroAtividadeRequestDTO dto) {
        RegistroAtividadeResponseDTO criado = service.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroAtividadeResponseDTO> alterar(@PathVariable Long id,
            @Valid @RequestBody RegistroAtividadeRequestDTO dto) {
        RegistroAtividadeResponseDTO atualizado = service.alterar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
