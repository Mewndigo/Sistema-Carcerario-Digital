package com.cefet.sistema_carcerario_digital.dto;

import com.cefet.sistema_carcerario_digital.entities.TipoUsuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PessoaRequestDTO extends BaseDTO {
    
    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 4, message = "O nome deve ter no mínimo 4 caracteres.")
    private String nome;

    @NotBlank(message = "O campo 'cpf' é obrigatório.")
    @Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres.")
    private String cpf;

    public PessoaRequestDTO() {
    }

    public PessoaRequestDTO(Long id, String nome, String cpf, String login, String senha, TipoUsuario tipo) {
        super(id);
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
