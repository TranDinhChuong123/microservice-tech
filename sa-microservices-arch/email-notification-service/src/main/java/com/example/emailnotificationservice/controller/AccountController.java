package com.example.emailnotificationservice.controller;

import com.example.emailnotificationservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/emails")
public class AccountController {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/new")
    public String sendKafkaToPic(@RequestBody EmailMessage emailMessage) {
        // Tạo tài khoản và thông điệp thống kê
        Statistic statistic = new Statistic("Tài khoản " + emailMessage.getToName() + " đã được tạo", new Date());

        // Gửi email thông báo
        Message message = new Message();
        message.setTo(emailMessage.getTo());
        message.setToName(emailMessage.getToName());
        message.setSubject("Thông báo đăng ký học phần");
        message.setContent(emailMessage.getSubjectCourses());

        // Đăng thông điệp lên các topic Kafka
        kafkaTemplate.send("notification", message);

        kafkaTemplate.send("statistic", statistic);

        return "success";
    }
}

