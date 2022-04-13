package com.thiagodev.springprojectbasic.Models.Dto;

import com.thiagodev.springprojectbasic.Models.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Integer id;
    private String nome;
    private Double preco;


    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto obj) {
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
    }


}
