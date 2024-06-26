package com.example.emailnotificationservice.services;

import com.example.emailnotificationservice.models.Message;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;


@Service
public class EmailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void sendEmail(Message message) throws MessagingException, MessagingException, jakarta.mail.MessagingException {
        logger.info("start... sending email");
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariable("name", message.getToName());
            context.setVariable("content", message.getContent());
            String html = templateEngine.process("confirmation-email", context);

            helper.setTo(message.getTo());
            helper.setText(html, true);
            helper.setSubject(message.getSubject());
            helper.setFrom(from);

            javaMailSender.send(msg);
            logger.info("Email sent successfully to: " + message.getTo());

        } catch (Exception e) {
            // Log lỗi nếu gặp vấn đề khi gửi email
            logger.error("Failed to send email to: " + message.getTo(), e);
        }
    }

}
