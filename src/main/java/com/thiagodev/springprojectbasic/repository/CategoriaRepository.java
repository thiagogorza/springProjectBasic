package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}