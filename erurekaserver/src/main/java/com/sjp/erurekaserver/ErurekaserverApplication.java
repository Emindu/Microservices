package com.sjp.erurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ErurekaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErurekaserverApplication.class, args);
    }

}
