package lk.ac.sjp.foe.co4353.g5.reputationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ReputationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReputationServiceApplication.class, args);
    }

}
