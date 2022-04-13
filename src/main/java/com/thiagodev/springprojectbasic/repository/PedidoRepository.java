package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}