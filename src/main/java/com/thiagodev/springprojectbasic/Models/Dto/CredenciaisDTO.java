package com.thiagodev.springprojectbasic.Models.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CredenciaisDTO implements Serializable {

    private String email;
    private String senha;
}
