package com.thiagodev.springprojectbasic.Models.Dto;


import com.thiagodev.springprojectbasic.Models.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaDto {

    private Long id;
    private String nome;


    public CategoriaDto(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();

    }

}
