package com.thiagodev.springprojectbasic.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thiagodev.springprojectbasic.Models.Pagamento.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date instante;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "pedido") // necessário para nao dar erro transient
    private Pagamento pagamento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(instante, pedido.instante) && Objects.equals(cliente, pedido.cliente) && Objects.equals(endereco, pedido.endereco) && Objects.equals(pagamento, pedido.pagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instante, cliente, endereco, pagamento);
    }

    public Pedido(Long id, Date instante, Cliente cliente, Endereco endereco) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.endereco = endereco;
    }

            public Pedido(){

            }
}
