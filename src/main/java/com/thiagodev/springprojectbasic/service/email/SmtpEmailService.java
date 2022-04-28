package com.thiagodev.springprojectbasic.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService{

    @Autowired //instancia os dados de email do aplicattion.properties relacionados ao email (classe do springFramework)
    private MailSender mailSender;

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        mailSender.send(msg);
    }
}
