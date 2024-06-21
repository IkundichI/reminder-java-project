package com.technokratos;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@RequiredArgsConstructor
@EnableWebSecurity
@EnableScheduling
public class ReminderSpringBootProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReminderSpringBootProjectApplication.class, args);
    }
}
