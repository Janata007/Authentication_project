package com.example.security;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
//@ComponentScan({"com.example.security"})
public class SecurityApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(SecurityApplication.class, args);

    }


}
