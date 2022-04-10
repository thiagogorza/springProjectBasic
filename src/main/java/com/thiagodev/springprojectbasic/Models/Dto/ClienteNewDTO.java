package com.thiagodev.springprojectbasic.Models.Dto;

import com.thiagodev.springprojectbasic.service.validation.ClienteInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@ClienteInsert
public class ClienteNewDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5,max = 120,message = "Tamanho deve ser entre 5 e 120 carateres")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "email inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpfOuCnpj;
    private Integer tipoCliente;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;

    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Long cidadeId;

    public ClienteNewDTO(){}
}
