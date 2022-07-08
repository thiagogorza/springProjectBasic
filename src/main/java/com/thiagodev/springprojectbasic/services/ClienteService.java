package com.thiagodev.springprojectbasic.services;

import com.thiagodev.springprojectbasic.Models.Cidade;
import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.Models.Dto.ClienteDTO;
import com.thiagodev.springprojectbasic.Models.Dto.ClienteNewDTO;
import com.thiagodev.springprojectbasic.Models.Endereco;
import com.thiagodev.springprojectbasic.Models.enums.Perfil;
import com.thiagodev.springprojectbasic.Models.enums.TipoCliente;
import com.thiagodev.springprojectbasic.services.exceptions.AuthorizationException;
import com.thiagodev.springprojectbasic.repository.ClienteRepository;
import com.thiagodev.springprojectbasic.repository.EnderecoRepository;
import com.thiagodev.springprojectbasic.services.exceptions.DataIntegrityException;
import com.thiagodev.springprojectbasic.services.exceptions.ObjectNotFoundException;
import com.thiagodev.springprojectbasic.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private BCryptPasswordEncoder pe;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    public Cliente findById(Integer id) {

        UserSS user = UserService.authenticated();
        if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
           throw new AuthorizationException("Acesso negado");
        }

        Optional<Cliente> obj = clienteRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id " +
                id + ",tipo:"  + Cliente.class.getName() ));

    }
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Transactional    // para garantir que irá salvar um cliente e um endereço na mesma transação
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
       enderecoRepository.saveAll(cliente.getEnderecos()); // para salvar o endereço no metodo post
        return cliente;

    }

    public Cliente update(Cliente cliente) {
       Cliente newCliente = findById(cliente.getId()); //feito para atualizar apenas os argumentos enviados (exemplo: atulizar só nome e email)
       updateData(newCliente,cliente);
        return clienteRepository.save(newCliente);
    }


    public void delete(Integer id) {
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é Possivel excluir uma cliente que possui produtos");
        }
    }

    public Page<Cliente> findPage(Pageable pageable) {

        return clienteRepository.findAll(pageable);
    }

    public Cliente fromDto(ClienteDTO objDto){

        return new Cliente (objDto.getId(),objDto.getName(),objDto.getEmail(),null,null,null);

    }
    public Cliente fromDto(ClienteNewDTO objDto) {
        Cliente cliente = new Cliente (null,objDto.getName(),objDto.getEmail(),objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()), pe.encode(objDto.getSenha()));
        Cidade cidade = new Cidade(objDto.getCidadeId(),null,null);
        Endereco endereco = new Endereco(null,objDto.getLogradouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro()
                ,objDto.getCep(),cliente,cidade);
        cliente.getEnderecos().add(endereco); // para o cliente conhecer o endereço.
        cliente.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2() !=null){
            cliente.getTelefones().add((objDto.getTelefone2()));
        }
        if(objDto.getTelefone3() !=null){
            cliente.getTelefones().add((objDto.getTelefone3()));
        }
    return cliente;

    }
    private void updateData(Cliente newCliente, Cliente obj){

        newCliente.setName(obj.getName());
        newCliente.setEmail(obj.getEmail());

    }


}
