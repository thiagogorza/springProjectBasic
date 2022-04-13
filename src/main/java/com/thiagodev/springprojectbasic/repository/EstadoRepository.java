package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}