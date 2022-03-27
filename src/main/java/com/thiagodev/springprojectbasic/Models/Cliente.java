package com.thiagodev.springprojectbasic.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente; // colocou tipo cliente como intenger dentro da classe,para que apenas o cod da classe TipoCliente
                                    // seja armazenado

    @ElementCollection // cria uma tabela nova "telefone" tendo como chave primaria o id do cliente
    @CollectionTable(name="telefone")
    private Set<String> telefones = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy="cliente")
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
