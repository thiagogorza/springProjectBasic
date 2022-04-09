package com.thiagodev.springprojectbasic.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

     // para vir os objetos associados a categoria
    @ManyToMany
    @JoinTable(name="produto_categoria",joinColumns = @JoinColumn(name = "categoria_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtoList = new ArrayList<>();

    public Categoria(Long id, String nome) {
        this.id = id;
        this.name = nome;
    }

    public Categoria(){}

}
