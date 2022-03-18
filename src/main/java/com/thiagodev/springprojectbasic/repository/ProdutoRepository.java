package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}