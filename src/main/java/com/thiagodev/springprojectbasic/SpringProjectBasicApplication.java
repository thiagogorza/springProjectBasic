package com.thiagodev.springprojectbasic;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringProjectBasicApplication implements CommandLineRunner {

    final
    CategoriaRepository categoriaRepository;

    public SpringProjectBasicApplication(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectBasicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null,"Eletronicos");
        Categoria cat2 = new Categoria(null,"Escritório");

        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
    }
}
