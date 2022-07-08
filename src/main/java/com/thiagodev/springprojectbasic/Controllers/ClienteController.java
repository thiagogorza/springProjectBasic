package com.thiagodev.springprojectbasic.Controllers;

import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.Models.Dto.ClienteDTO;
import com.thiagodev.springprojectbasic.Models.Dto.ClienteNewDTO;
import com.thiagodev.springprojectbasic.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping(value = {"{id}"})
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        Cliente obj = clienteService.findById(id);
        return ResponseEntity.ok().body(obj);

    }
    @GetMapping
    public ResponseEntity<?> findAll() { // findall apenas do DTO que está sem a lista de produtos,apenas id e nome da cliente.

        List<ClienteDTO> clienteDTOS = new ArrayList<>();
        List<Cliente> clienteList = clienteService.findAll();

// converte uma lista em outra lista (foi feito no curso dessa forma, porém preferi utilizar o beanUtils(mais legível)
//        for (Cliente cliente : clienteList){
//
//            ClienteDTO clienteDto = new ClienteDTO();
//
//            BeanUtils.copyProperties(cliente,clienteDto);
//
//            clienteDTOS.add(clienteDto);
//        }
//
//        return ResponseEntity.ok(clienteDTOS);
                List<ClienteDTO> clienteDtos = clienteList.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok(clienteDTOS);
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
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
        Cliente cliente = clienteService.fromDto(clienteDTO);
        cliente.setId(id);
        clienteService.update(cliente);
        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        clienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(@PageableDefault(page =0,size = 24,direction = Sort.Direction.ASC,sort = "name") Pageable pageable) {

        Page<Cliente> clientePage = clienteService.findPage(pageable);
        Page<ClienteDTO> clientePageDtos = clientePage.map(obj -> new ClienteDTO(obj));



        return ResponseEntity.ok().body(clientePageDtos);
    }


}
