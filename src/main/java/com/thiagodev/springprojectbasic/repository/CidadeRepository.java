package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}