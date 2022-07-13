package com.thiagodev.springprojectbasic.services;

import com.thiagodev.springprojectbasic.Models.Cliente;
import com.thiagodev.springprojectbasic.services.email.EmailService;
import com.thiagodev.springprojectbasic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    public ClienteService service;

    @Autowired
    public EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email) {

        Cliente cliente = service.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();
        cliente.setSenha(pe.encode(newPass));
        service.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);

    }

    //cria um password aleatório com 10 elementos.
    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randowChar();
        }
        return new String(vet);
    }

    private char randowChar() {
        int opt = rand.nextInt(3);

       //gera um digito
        if(opt == 0) {

            return (char) (rand.nextInt(10) + 48); // unicode do "0" é "48", entao , para gerar um digito de 0 a 9, soma-se 10 ao numero 48
        }
        //gera letra maiuscula
        else if (opt == 1){
            return (char) (rand.nextInt(26) + 65);
        }

        //gera letra minuscula
        else{
            return (char) (rand.nextInt(26) + 97);

        }
    }
}
