package com.thiagodev.springprojectbasic;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Produto;
import com.thiagodev.springprojectbasic.repository.CategoriaRepository;
import com.thiagodev.springprojectbasic.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringProjectBasicApplication implements CommandLineRunner {

    final
    private CategoriaRepository categoriaRepository;


    final
    private ProdutoRepository produtoRepository;


    public SpringProjectBasicApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringProjectBasicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null,"Eletronicos");
        Categoria cat2 = new Categoria(null,"Escritório");
        Produto p1 = new Produto(null,"Computador",2000.00);
        Produto p2 = new Produto(null,"Impressora",800.00);
        Produto p3 = new Produto(null,"Mouse",80.00);

//        p1.getCategoriaList().addAll(Arrays.asList(cat1));
//        p2.getCategoriaList().addAll(Arrays.asList(cat1,cat2));
//        p3.getCategoriaList().addAll(Arrays.asList(cat1));

//        cat1.getProdutoList().addAll(Arrays.asList(p1,p2,p3));
//        cat2.getProdutoList().addAll(Arrays.asList(p2));



        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
    }
}
