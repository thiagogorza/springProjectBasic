package com.thiagodev.springprojectbasic.repository;

import com.thiagodev.springprojectbasic.Models.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}