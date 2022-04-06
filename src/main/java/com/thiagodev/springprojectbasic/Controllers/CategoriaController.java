package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = {"{id}"})
    public ResponseEntity<Categoria> find(@PathVariable Long id) {
        Categoria obj = categoriaService.findByid(id);
        return ResponseEntity.ok(obj);

    }

    @PostMapping
    public Categoria insert(@RequestBody Categoria categoria) {
       categoriaService.insert(categoria);
        return categoria;
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Long id){
        categoria.setId(id);
        categoriaService.update(categoria);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id) {

      categoriaService.delete(id);
      return ResponseEntity.noContent().build();

    }

}
