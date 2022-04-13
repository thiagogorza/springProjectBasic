package com.thiagodev.springprojectbasic.Models.Dto;

import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.service.validation.ClienteUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@ClienteUpdate
public class ClienteDTO {

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5,max = 120,message = "Tamanho deve ser entre 5 e 120 carateres")
    private String name;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "email inválido")
    private String email;

public ClienteDTO(){}

    public ClienteDTO(Cliente cliente){
    id = cliente.getId();
    name = cliente.getName();
    email = cliente.getEmail();
    }


}
