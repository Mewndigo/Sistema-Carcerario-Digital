package com.cefet.sistema_carcerario_digital.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.sistema_carcerario_digital.dto.PessoaRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.PessoaResponseDTO;
import com.cefet.sistema_carcerario_digital.services.PessoaService;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pessoas")
// @Tag(name = "Pessoas", description = "Gerenciar Pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    // @Operation(summary = "Listar pessoas", description = "Lista todas as
    // pessoas.")
    public ResponseEntity<List<PessoaResponseDTO>> listar() {
        List<PessoaResponseDTO> lista = pessoaService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    // @Operation(summary = "Buscar pessoa por ID")
    public ResponseEntity<PessoaResponseDTO> buscar(@PathVariable UUID id) {
        PessoaResponseDTO dto = pessoaService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    // @Operation(summary = "Cadastrar pessoa")
    public ResponseEntity<PessoaResponseDTO> inserir(@Valid @RequestBody PessoaRequestDTO dto) {
        PessoaResponseDTO novo = pessoaService.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/{id}")
    // @Operation(summary = "Atualizar dados da pessoa")
    public ResponseEntity<PessoaResponseDTO> alterar(@PathVariable UUID id, @Valid @RequestBody PessoaRequestDTO dto) {

        PessoaResponseDTO atualizado = pessoaService.alterar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @DeleteMapping("/{id}")
    // @Operation(summary = "Remover pessoa")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        pessoaService.excluir(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}