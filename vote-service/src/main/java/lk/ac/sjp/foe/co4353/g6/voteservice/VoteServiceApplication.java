package lk.ac.sjp.foe.co4353.g6.voteservice;

import lk.ac.sjp.foe.co4353.g6.voteservice.models.AnswerVote;
import lk.ac.sjp.foe.co4353.g6.voteservice.models.QuestionVote;
import lk.ac.sjp.foe.co4353.g6.voteservice.repository.AnswerVotesRepository;
import lk.ac.sjp.foe.co4353.g6.voteservice.repository.QuestionVotesRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class VoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteServiceApplication.class, args);
    }


    @Bean
    ApplicationRunner init(AnswerVotesRepository repository) {
        return args -> Stream.of(
                new AnswerVote(
                        1, 2 , 1
                ),
                new AnswerVote(
                        1 , 3 , 0
                ),
                new AnswerVote(
                        2, 1 , 1
                ),
                new AnswerVote(
                        2 , 3 , 1
                ),
                new AnswerVote(
                        3, 1 , -1
                ),
                new AnswerVote(
                        3, 2 , -1
                ),
                new AnswerVote(
                        4, 2 , 1
                ),
                new AnswerVote(
                        4 , 3 , 0
                ),
                new AnswerVote(
                        5, 1 , 1
                ),
                new AnswerVote(
                        5 , 3 , 1
                ),
                new AnswerVote(
                        6, 1 , 0
                ),
                new AnswerVote(
                        6, 2 , -1
                )
        ).forEach(repository::save);
    }

    @Bean
    ApplicationRunner init2(QuestionVotesRepository repository) {
        return args -> Stream.of(
                new QuestionVote(
                        2, 2 , 1
                ),
                new QuestionVote(
                        2 , 3 , 0
                ),
                new QuestionVote(
                        3, 1 , 1
                ),
                new QuestionVote(
                        3 , 3 , 1
                ),
                new QuestionVote(
                        1, 1 , -1
                ),
                new QuestionVote(
                        1, 2 , -1
                )
        ).forEach(repository::save);
    }
}
