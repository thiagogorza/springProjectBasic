package com.thiagodev.springprojectbasic.Models.Dto;


import com.thiagodev.springprojectbasic.Models.Categoria;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class CategoriaDTO implements Serializable {

    private Integer id;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;


    public CategoriaDTO(Categoria categoria){
        id = categoria.getId();
        nome = categoria.getNome();

    }

    public CategoriaDTO(){
    }

}
