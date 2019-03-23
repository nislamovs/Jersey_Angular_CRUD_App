package com.jerseyexample.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class JerseydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JerseydemoApplication.class, args);
    }
}

