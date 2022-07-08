package com.thiagodev.springprojectbasic.services;

import com.thiagodev.springprojectbasic.Models.Pagamento.Pagamento;
import com.thiagodev.springprojectbasic.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PagamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;


    public Pagamento save(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);

    }

}
