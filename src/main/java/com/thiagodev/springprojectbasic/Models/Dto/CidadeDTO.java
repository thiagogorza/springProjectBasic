package com.thiagodev.springprojectbasic.Models.Dto;

import com.thiagodev.springprojectbasic.Models.Cidade;;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO {

    public CidadeDTO(Cidade obj) {
        estadoId = obj.getEstado().getId();
        nome = obj.getNome();
    }


    private Integer estadoId;
    private String nome;
}
