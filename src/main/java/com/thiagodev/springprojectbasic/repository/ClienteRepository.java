package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}