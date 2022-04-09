package com.thiagodev.springprojectbasic.service;

import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.Models.Dto.ClienteDTO;
import com.thiagodev.springprojectbasic.repository.ClienteRepository;
import com.thiagodev.springprojectbasic.service.exception.DataIntegrityException;
import com.thiagodev.springprojectbasic.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    public Cliente findByid(Long id) {


        Optional<Cliente> obj = clienteRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " +
                id + ",tipo:"  + Cliente.class.getName() ));

    }
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        return clienteRepository.save(cliente);

    }

    public Cliente update(Cliente cliente) {
       Cliente newCliente = findByid(cliente.getId()); //feito para atualizar apenas os argumentos enviados (exemplo: atulizar só nome e email)
       updateData(newCliente,cliente);
        return clienteRepository.save(newCliente);
    }


    public void delete(Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é Possivel excluir uma cliente que possui produtos");
        }
    }

    public Page<Cliente> findPage(Pageable pageable) {
//        Integer page, Integer linesPerPage, String direction, String ordeBy
//       PageRequest pageRequest =  PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),ordeBy);

        return clienteRepository.findAll(pageable);
    }

    public Cliente fromDto(ClienteDTO objDto){

        return new Cliente (objDto.getId(),objDto.getName(),objDto.getEmail(),null,null);

    }

    private void updateData(Cliente newCliente, Cliente obj){

        newCliente.setName(obj.getName());
        newCliente.setEmail(obj.getEmail());

    }

}
