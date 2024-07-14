package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.epam")
@EnableEurekaClient
public class LibraryMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryMicroserviceApplication.class, args);
    }

}
