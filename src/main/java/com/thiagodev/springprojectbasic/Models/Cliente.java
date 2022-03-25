package com.thiagodev.springprojectbasic.Models;


import com.thiagodev.springprojectbasic.Models.enums.TipoCliente;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String cpfOuCnpj;
    private TipoCliente tipoCliente;



}
