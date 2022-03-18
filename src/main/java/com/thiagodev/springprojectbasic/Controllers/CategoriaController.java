package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Models.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {


        @GetMapping
        public List<Categoria> listar(){

        Categoria cat = new Categoria(1L,"Eletronicos");
        Categoria cat2 = new Categoria(2L,"Moveis");

        List<Categoria> lista = new ArrayList<>();

        lista.add(cat);
        lista.add(cat2);

        return lista;

    }

}
