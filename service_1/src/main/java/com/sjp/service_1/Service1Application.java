package com.sjp.service_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Service1Application {

    @Bean
    public RestTemplate getrestTemplate(){
        return new RestTemplate();
    }


    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }
    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }

}
