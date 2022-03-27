package com.thiagodev.springprojectbasic.Models.Pedido;

import com.thiagodev.springprojectbasic.Models.Produto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable // usado para informar que a classe é um subtipo (não será criada uma nova tabela, ela entra na tabela itemPedido)
public class ItemPedidoPk implements Serializable {
    private static final long serialVersionUID = -3997869656643892657L; // classe criada para fazer a associacao entre pedido e produto do itemPedido

    @ManyToOne
    @JoinColumn(name = "pedido_id")// o Id da classe ItemPedido, será o id do pedido junto com o id do produto.
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

}
