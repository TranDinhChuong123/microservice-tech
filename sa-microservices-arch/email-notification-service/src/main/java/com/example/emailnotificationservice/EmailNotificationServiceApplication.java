package com.example.emailnotificationservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
@EnableDiscoveryClient
public class EmailNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailNotificationServiceApplication.class, args);
    }
    @Bean
    JsonMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @Bean
    NewTopic notification() {
        // topic name, partition numbers, replication number
        return new NewTopic("notification", 1, (short) 1);
    }

    @Bean
    NewTopic statistic() {
        return new NewTopic("statistic", 1, (short) 1);
    }
}
