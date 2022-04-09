package com.thiagodev.springprojectbasic.Models.Dto;


import com.thiagodev.springprojectbasic.Models.Categoria;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


public class CategoriaDTO implements Serializable {

    private Long id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String name;


    public CategoriaDTO(Categoria categoria){
        id = categoria.getId();
        name = categoria.getName();

    }

    public CategoriaDTO(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }
}
