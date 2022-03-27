package com.thiagodev.springprojectbasic.Models;


import com.thiagodev.springprojectbasic.Models.enums.TipoCliente;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente; // colocou tipo cliente como intenger dentro da classe,para que apenas o cod da classe TipoCliente
                                    // seja armazenado

    @ElementCollection
    private Set<String> telefones = new HashSet<>();

    @OneToMany(mappedBy="cliente", orphanRemoval = true)
    private List<Endereco> enderecos;

    public Cliente(Long id, String name, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = tipoCliente.getCod();
    }

    public Cliente() {
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCod();
    }
}
