package com.thiagodev.springprojectbasic.services.validation;

import com.thiagodev.springprojectbasic.Controllers.exception.FieldMessage;
import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.Models.Dto.ClienteDTO;
import com.thiagodev.springprojectbasic.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    ClienteRepository clienteRepository;


    @Override
    public void initialize(ClienteUpdate ann) {
    }
    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        Map<String,String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            // o map recupera a uri da requisicao, nesse caso, vai recuperar o ID
        Long uriId = Long.parseLong(map.get("id"));
        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if(aux != null && !aux.getId().equals(uriId)){ // se email for diferente de null e o id do aux for diferente do da requisicao, em portuques, se ja existe um email para esse cliente que vai ser cadastrado e o email que foi mandado ja existe em outro cliente, gera a execao
            list.add(new FieldMessage("email","Email já cadastrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
