package lk.ac.sjp.foe.co4353.g6.questionservice.service;

import lk.ac.sjp.foe.co4353.g6.questionservice.dto.LongListWrapper;
import lk.ac.sjp.foe.co4353.g6.questionservice.dto.LongLongMapWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AnswerService {

    private final RestTemplate restTemplate;

    public AnswerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public LongLongMapWrapper getQuestionIdsVotesCounts(List<Long> questionIds) {
        return restTemplate.postForObject(
                "http://answer-service/answers/questions/count",
                new LongListWrapper(questionIds),
                LongLongMapWrapper.class
        );
    }
}
