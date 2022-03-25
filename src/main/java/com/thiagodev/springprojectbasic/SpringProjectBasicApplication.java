package com.thiagodev.springprojectbasic;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Cidade;
import com.thiagodev.springprojectbasic.Models.Estado;
import com.thiagodev.springprojectbasic.Models.Produto;
import com.thiagodev.springprojectbasic.repository.CategoriaRepository;
import com.thiagodev.springprojectbasic.repository.CidadeRepository;
import com.thiagodev.springprojectbasic.repository.EstadoRepository;
import com.thiagodev.springprojectbasic.repository.ProdutoRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringProjectBasicApplication implements ApplicationRunner {
    final
    private CategoriaRepository categoriaRepository;


    final
    private ProdutoRepository produtoRepository;

    final
    private CidadeRepository cidadeRepository;

    final
    private EstadoRepository estadoRepository;




    public SpringProjectBasicApplication(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringProjectBasicApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Categoria cat1 = new Categoria(null,"Eletronicos");
        Categoria cat2 = new Categoria(null,"Escritório");
        Produto p1 = new Produto(null,"Computador",2000.00);
        Produto p2 = new Produto(null,"Impressora",800.00);
        Produto p3 = new Produto(null,"Mouse",80.00);

        p1.getCategoriaList().addAll(Arrays.asList(cat1));
        p2.getCategoriaList().addAll(Arrays.asList(cat1,cat2));
        p3.getCategoriaList().addAll(Arrays.asList(cat1));

        cat1.getProdutoList().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutoList().addAll(Arrays.asList(p2));


        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));

        Estado est1 = new Estado(null,"Minas Gerais");
        Estado est2 = new Estado(null,"São Paulo");
        Cidade c1 = new Cidade(null,"Uberlandia",est1);
        Cidade c2 = new Cidade(null,"Sao Paulo",est2);
        Cidade c3 = new Cidade(null,"Campinas",est2);

        est1.getCidadeList().addAll(Arrays.asList(c1));
        est2.getCidadeList().addAll(Arrays.asList(c2,c3));

        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));



    }
}
