package lk.ac.sjp.foe.co4353.g6.userservice;

import lk.ac.sjp.foe.co4353.g6.userservice.model.User;
import lk.ac.sjp.foe.co4353.g6.userservice.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner init(UserRepository repository) {
        return args -> Stream.of(
                new User(
                       "Mark snowden", "United States", 20
                ),
                new User(
                        "Tharaka Amarathunga", "Sri Lanka", 30
                )
        ).forEach(repository::save);
    }
}
