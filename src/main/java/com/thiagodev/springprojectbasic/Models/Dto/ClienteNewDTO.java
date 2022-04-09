package com.thiagodev.springprojectbasic.Models.Dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClienteNewDTO implements Serializable {

    private String name;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Long cidadeId;

    public ClienteNewDTO(){}
}
