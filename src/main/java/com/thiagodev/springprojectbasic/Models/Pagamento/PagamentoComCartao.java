package com.thiagodev.springprojectbasic.Models.Pagamento;

import com.thiagodev.springprojectbasic.Models.Pedido;
import com.thiagodev.springprojectbasic.Models.enums.EstadoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class PagamentoComCartao extends Pagamento{

    private Integer numeroDeParcelas;  // se der erro é pq tem que criar o construtor através da superclasse

    public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public PagamentoComCartao() {

    }
}
