package com.isa.users.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailSender{

    private final JavaMailSender mailSender;
    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    @Async
    public void sendEmail(String to, String email, String type) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            if(type.equals("REG"))
                helper.setSubject("Confirm your email");
            else if(type.equals("RES"))
                helper.setSubject("Reservation made");
            else if(type.equals("REV"))
                helper.setSubject("Revision approved");
            else if(type.equals("SUB"))
                helper.setSubject("Your subscriptions added new actions");
            else if(type.equals("DEL"))
                helper.setSubject("Delete request");
            else if(type.equals("REQ_APPR"))
                helper.setSubject("Registration request approved");
            else if(type.equals("REQ_REJ"))
                helper.setSubject("Registration request rejected");
            else if(type.equals("COM"))
                helper.setSubject("Complaint answer");

            helper.setFrom("ognjencivcic23@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("Failed to send email for: " + email + "\n" + e);
            throw new IllegalArgumentException("Failed to send email for: " + email);
        }
    }
}
