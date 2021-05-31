package lk.ac.sjp.foe.co4353.g6.answerservice.service;

import lk.ac.sjp.foe.co4353.g6.answerservice.dto.LongListWrapper;
import lk.ac.sjp.foe.co4353.g6.answerservice.dto.LongLongMapWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VoteService {

    private final RestTemplate restTemplate;

    public VoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<Long, Long> getVoteCounts(List<Long> answersIds) {
        final LongLongMapWrapper wrappedResponse = restTemplate.postForObject(
                "http://vote-service/votes/answers",
                new LongListWrapper(answersIds),
                LongLongMapWrapper.class
        );
        return Optional
                .ofNullable(wrappedResponse)
                .orElseGet(LongLongMapWrapper::new)
                .getBody();
    }

    public Long getVoteCounts(Long answerId) {
        return getVoteCounts(Collections.singletonList(answerId)).get(answerId);
    }
}
