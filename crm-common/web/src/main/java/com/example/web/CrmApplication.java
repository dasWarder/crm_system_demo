package com.example.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = { "com.example.model" })
@EnableJpaRepositories(basePackages = { "com.example.repository" })
@SpringBootApplication(scanBasePackages = { "com.example.service", "com.example.mapper", "com.example.web" })
public class CrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }
}
