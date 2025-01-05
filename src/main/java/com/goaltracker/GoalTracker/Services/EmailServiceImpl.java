package com.goaltracker.GoalTracker.Services;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Component
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmai(String message) {
        
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException, UnsupportedEncodingException {
    
        // SimpleMailMessage message = new SimpleMailMessage(); 
        // message.setFrom("noreply@baeldung.com");
        // message.setTo(to); 
        // message.setSubject(subject); 
        // message.setText(text);
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setSubject("Testing 1,2,3");
        message.setFrom("test@mail.com", "SendGrid Tester");
        message.setTo("kalio.ofvil@gmail.com");
        message.setText("testing");

        mailSender.send(mimeMessage);
        
    }
    
}
