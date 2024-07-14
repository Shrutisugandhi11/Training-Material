package com.epam;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuizMangementSystemRestfullApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizMangementSystemRestfullApplication.class, args);
    }

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

}
