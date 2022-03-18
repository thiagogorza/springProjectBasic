package com.thiagodev.springprojectbasic.Models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToMany
    @JoinTable(name="PRODUTO_CATEGORIA",joinColumns = @JoinColumn(name = "categoria_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtoList;

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
