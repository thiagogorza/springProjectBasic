package com.thiagodev.springprojectbasic.Models.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class EmailDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "email inválido")
    private String email;
}
