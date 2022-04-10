package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.Models.Dto.ClienteDTO;
import com.thiagodev.springprojectbasic.Models.Dto.ClienteNewDTO;
import com.thiagodev.springprojectbasic.service.ClienteService;
import com.thiagodev.springprojectbasic.service.validation.ClienteInsert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping(value = {"{id}"})
    public ResponseEntity<?> findbyId(@PathVariable Long id) {
        Cliente obj = clienteService.findByid(id);
        return ResponseEntity.ok(obj);

    }
    @GetMapping
    public ResponseEntity<?> findAll() { // findall apenas do DTO que está sem a lista de produtos,apenas id e nome da cliente.

        List<ClienteDTO> clienteDTOS = new ArrayList<>();
        List<Cliente> clienteList = clienteService.findAll();


        for (Cliente cliente : clienteList){

            ClienteDTO clienteDto = new ClienteDTO();

            BeanUtils.copyProperties(cliente,clienteDto);

            clienteDTOS.add(clienteDto);
        }

        return ResponseEntity.ok(clienteDTOS);
        //        List<ClienteDto> clienteDtos = clienteList.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
        // converte uma lista em outra lista (foi feito no curso dessa forma, porém preferi utilizar o beanUtils(mais legível)
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
        Cliente obj = clienteService.fromDto(objDto);
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody Cliente cliente, @PathVariable Long id){
        cliente.setId(id);
        clienteService.update(cliente);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        clienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(@PageableDefault(page =0,size = 24,direction = Sort.Direction.ASC,sort = "name") Pageable pageable) {

        Page<Cliente> clientePage = clienteService.findPage(pageable);
        Page<ClienteDTO> clientePageDtos = clientePage.map(obj -> new ClienteDTO(obj));



        return ResponseEntity.ok().body(clientePageDtos);
    }


}
