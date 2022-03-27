package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}