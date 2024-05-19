package com.programingtechie.authenticationservice;


import com.programingtechie.authenticationservice.entity.Account;
import com.programingtechie.authenticationservice.entity.Role;
import com.programingtechie.authenticationservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RequiredArgsConstructor
public class AuthenticationServiceApplication {
    private final AuthService authService;

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner sampleDataRunner() {
        return args -> {
            Account account1 = Account.builder().
                    username("20002835")
                    .password("pass")
                    .role(Role.ADMIN)
                    .build();

            Account account2 = Account.builder().
                    username("tranbinhan123")
                    .password("pass")
                    .role(Role.ADMIN)
                    .build();

            authService.saveAccount(account1);
            authService.saveAccount(account2);

        };
    }

}
