package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Models.Categoria;
import com.thiagodev.springprojectbasic.Models.Dto.CategoriaDto;
import com.thiagodev.springprojectbasic.service.CategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() { // findall apenas do DTO que está sem a lista de produtos,apenas id e nome da categoria.

        List<CategoriaDto> categoriaDtos = new ArrayList<>();
        List<Categoria> categoriaList = categoriaService.findAll();


        for (Categoria categoria : categoriaList){

            CategoriaDto categoriaDto = new CategoriaDto();

            BeanUtils.copyProperties(categoria,categoriaDto);

            categoriaDtos.add(categoriaDto);
        }
//        List<CategoriaDto> categoriaDtos = categoriaList.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
        // converte uma lista em outra lista (foi feito no curso dessa forma, porém preferi utilizar o beanUtils(mais legível)

        return ResponseEntity.ok(categoriaDtos);

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

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDto>> findPage(@PageableDefault(page =0,size = 24,direction = Sort.Direction.ASC,sort = "nome") Pageable pageable) {
//        @RequestParam(value = "page",defaultValue = "0") Integer page,
//        @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage, // 24 pois é multiplo de 2,3,4, ficando fácil a divisão na pagina(dependendo do tamanho da tela)
//        @RequestParam(value = "direction",defaultValue = "ASC")String direction, //ascendente ou descendente (DESC)
//        @RequestParam(value = "ordeBy",defaultValue = "nome") String ordeBy,
        Page<Categoria> categoriaPage = categoriaService.findPage(pageable);
        Page<CategoriaDto> categoriaPageDtos = categoriaPage.map(obj -> new CategoriaDto(obj));



        return ResponseEntity.ok().body(categoriaPageDtos);
    }

}
