package com.thiagodev.springprojectbasic.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thiagodev.springprojectbasic.Models.Pedido.ItemPedido;
import com.thiagodev.springprojectbasic.Models.Pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {
    private static final long serialVersionUID = -3997869656643892657L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;

    @JsonBackReference // vai omitir a lista de categorias para o produto (pois já foi referenciado na outra classe)
    @ManyToMany(mappedBy = "produtoList")
    private List<Categoria> categoriaList = new ArrayList<>();

    @OneToMany(mappedBy = "id.produto") // id.produto pois está mapeado dentro de itemPedidoPk, o id está em itemPedido e o produto dentro de ItemPedidoPk
    private Set<ItemPedido> itens = new HashSet<>();

    public List<Pedido> getPedidos (){
        List<Pedido> lista = new ArrayList<>();
        for (ItemPedido x : itens) {
          lista.add(x.getPedido());
        }
        return lista;
    }

    public Produto(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
}
