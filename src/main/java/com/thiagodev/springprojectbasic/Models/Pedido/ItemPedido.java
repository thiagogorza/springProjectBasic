package com.thiagodev.springprojectbasic.Models.Pedido;

import com.thiagodev.springprojectbasic.Models.Produto;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
public class ItemPedido implements Serializable {

    @EmbeddedId
    private ItemPedidoPk id = new ItemPedidoPk();

    private Double desconto;;
    private Integer quantidade;
    private Double preco;

    //Dentro do construtor, foi alterado o ItemPedidoPk pois não faria sentido ao olhar as informações.
    //Sendo assim, dentro do construtor, alterando ele para pedido e produto, foi setado o id do pedido e do produto
    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemPedido(){

    }

    public Pedido getPedido(){
        return id.getPedido();
    }
    public Produto getProduto(){
        return id.getProduto();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id) && Objects.equals(desconto, that.desconto) && Objects.equals(quantidade, that.quantidade) && Objects.equals(preco, that.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desconto, quantidade, preco);
    }
}
