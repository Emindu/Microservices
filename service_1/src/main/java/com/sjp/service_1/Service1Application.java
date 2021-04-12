package com.sjp.service_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class Service1Application {

    @Bean
    @LoadBalanced //use to tell client to do client side load balancing
    public RestTemplate getrestTemplate(){
        return new RestTemplate();
    }


    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }
    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }

}
