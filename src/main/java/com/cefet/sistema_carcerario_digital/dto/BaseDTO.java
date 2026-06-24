package com.cefet.sistema_carcerario_digital.dto;

public class BaseDTO {
    private Long id;

    public BaseDTO() {
    }

    public BaseDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
