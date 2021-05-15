package lk.ac.sjp.foe.co4353.g6.voteservice;

import lk.ac.sjp.foe.co4353.g6.voteservice.models.AnswerVote;
import lk.ac.sjp.foe.co4353.g6.voteservice.models.QuestionVote;
import lk.ac.sjp.foe.co4353.g6.voteservice.repository.AnswerVotesRepository;
import lk.ac.sjp.foe.co4353.g6.voteservice.repository.QuestionVotesRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class VoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteServiceApplication.class, args);
    }


    @Bean
    ApplicationRunner init(AnswerVotesRepository repository) {
        return args -> Stream.of(
                new AnswerVote( 1, 1 , 1
                ),
                new AnswerVote(
                   2 , 2 , 2
                )
        ).forEach(repository::save);
    }

    @Bean
    ApplicationRunner init2(QuestionVotesRepository repository) {
        return args -> Stream.of(
                new QuestionVote( 10, 10 , 10
                ),
                new QuestionVote(
                        11 , 11 , 11
                )
        ).forEach(repository::save);
    }
}
