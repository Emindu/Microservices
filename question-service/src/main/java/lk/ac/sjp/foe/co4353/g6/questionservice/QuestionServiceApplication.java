package lk.ac.sjp.foe.co4353.g6.questionservice;

import lk.ac.sjp.foe.co4353.g6.questionservice.model.Question;
import lk.ac.sjp.foe.co4353.g6.questionservice.repository.QuestionRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class QuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionServiceApplication.class, args);
    }

    // Temporary: add some dummy entries to table at init
    // todo: remove this and replace with a data.sql file
    @Bean
    ApplicationRunner init(QuestionRepository repository) {
        return args -> Stream.of(
                new Question(
                        "What are the differences between a pointer variable and a reference variable in C++?",
                        "I know references are syntactic sugar, so code is easier to read and write.\n\nBut what are the differences?",
                        1L,
                        1L,
                        new Date(),
                        new Date()
                ),
                new Question(
                        "What is the difference between public, protected, package-private and private in Java?",
                        "In Java, are there clear rules on when to use each of access modifiers, namely the default (package private), public, protected and private, while making class and interface and dealing with inheritance?",
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

