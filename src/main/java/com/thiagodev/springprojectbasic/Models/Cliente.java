package com.thiagodev.springprojectbasic.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thiagodev.springprojectbasic.Models.Pedido.Pedido;
import com.thiagodev.springprojectbasic.Models.enums.TipoCliente;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente; // colocou tipo cliente como intenger dentro da classe,para que apenas o cod da classe TipoCliente

    @JsonIgnore
    @OneToMany(mappedBy="cliente")
    private List<Pedido> pedidos = new ArrayList<>();                              // seja armazenado

    @ElementCollection // cria uma tabela nova "telefone" tendo como chave primaria o id do cliente
    @CollectionTable(name="telefone")
    private Set<String> telefones = new HashSet<>();

    
    @OneToMany(mappedBy="cliente",cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente(Long id, String name, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = (tipoCliente==null) ? null : tipoCliente.getCod();
    }

    public Cliente() {
    }

    public Cliente(Long id, String name, String email) {
    }

    public Cliente(Object o, String name, String email, String cpfOuCnpj, Integer tipoCliente, String logradouro, String numero, String complemento, String bairro, String cep, String telefone1, String telefone2, String telefone3, Long cidadeId) {
    }


    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return id != null && Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
