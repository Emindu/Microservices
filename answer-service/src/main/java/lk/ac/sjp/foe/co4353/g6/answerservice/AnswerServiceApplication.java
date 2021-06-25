package lk.ac.sjp.foe.co4353.g6.answerservice;

import lk.ac.sjp.foe.co4353.g6.answerservice.model.Answer;
import lk.ac.sjp.foe.co4353.g6.answerservice.repository.AnswerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class AnswerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnswerServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner init(AnswerRepository repository) {
        return args -> Stream.of(
                new Answer(
                        1L,
                        "A first Answer",
                        1L,
                        1L,
                        new Date(),
                        new Date()
                ),
                new Answer(
                        1L,
                        "A second answer",
                        1L,
                        1L,
                        new Date(),
                        new Date()
                )
        ).forEach(repository::save);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

