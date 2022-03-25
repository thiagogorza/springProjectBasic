package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}