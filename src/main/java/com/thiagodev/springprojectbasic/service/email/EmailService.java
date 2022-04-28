package com.thiagodev.springprojectbasic.service.email;

import com.thiagodev.springprojectbasic.Models.Pedido.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);
    void sendEmail(SimpleMailMessage msg);
}
