package com.cebon.restart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestartApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestartApplication.class, args);
    }

}
