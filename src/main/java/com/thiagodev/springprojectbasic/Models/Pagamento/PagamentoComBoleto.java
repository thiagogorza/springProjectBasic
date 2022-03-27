package com.thiagodev.springprojectbasic.Models.Pagamento;


import com.thiagodev.springprojectbasic.Models.Pedido;
import com.thiagodev.springprojectbasic.Models.enums.EstadoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@Entity
public class PagamentoComBoleto extends Pagamento {

    private Date dataVencimento;
    private Date dataPagamento;


    public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public PagamentoComBoleto() {

    }
}
