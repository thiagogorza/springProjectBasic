package com.thiagodev.springprojectbasic.Models.Dto;


import com.thiagodev.springprojectbasic.Models.Categoria;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


public class CategoriaDto implements Serializable {

    private Long id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;


    public CategoriaDto(Categoria categoria){
        id = categoria.getId();
        nome = categoria.getNome();

    }

    public CategoriaDto(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
