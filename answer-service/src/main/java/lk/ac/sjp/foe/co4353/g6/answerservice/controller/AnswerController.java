package lk.ac.sjp.foe.co4353.g6.answerservice.controller;

import lk.ac.sjp.foe.co4353.g6.answerservice.model.Answer;
import lk.ac.sjp.foe.co4353.g6.answerservice.dto.LongListWrapper;
import lk.ac.sjp.foe.co4353.g6.answerservice.dto.LongLongMapWrapper;
import lk.ac.sjp.foe.co4353.g6.answerservice.repository.AnswerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/answers")
public class AnswerController {
    final AnswerRepository answerRepository;

    AnswerController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<Answer> getAnswer(@PathVariable("answerId") Long answerId) {
        try {
            Answer answer = answerRepository.findByAnswerId(answerId);

            if (answer == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // TODO: replace with service call
                long votesCount = 0L;
                answer.setVoteCount(votesCount);
                return new ResponseEntity<>(answer, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Answer> postAnswer(@RequestBody Answer answerReq) {
        try {
            Date currentTime = new Date();
            Answer answer = answerRepository.save(new Answer(
                    answerReq.getQuestionId(),
                    answerReq.getText(),
                    answerReq.getCreatedBy(),
                    answerReq.getCreatedBy(),
                    currentTime,
                    currentTime
            ));
            return new ResponseEntity<>(answer, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Answer>> getAnswersForQuestion(
            @PathVariable("questionId") Long questionId) {

        try {
            List<Answer> answers = new ArrayList<>(answerRepository.findByQuestionId(questionId))
                    .stream()
                    .peek(answer -> {
                        long votesCount = 0L; //TODO: replace with service call
                        answer.setVoteCount(votesCount);
                    })
                    .collect(Collectors.toList());
            if (answers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(answers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/questions/count")
    public ResponseEntity<LongLongMapWrapper> getAnswersCountsForQuestions(
            @RequestBody LongListWrapper questionIdList) {

        try {
            Map<Long, Long> questionIdAnswersCountMap = new HashMap<>();
            questionIdList.getBody()
                    .forEach(questionId -> questionIdAnswersCountMap.put(
                            questionId,
                            answerRepository.countByQuestionId(questionId)
                    ));
            return new ResponseEntity<>(
                    new LongLongMapWrapper(questionIdAnswersCountMap), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Answer>> readQuestionsByUser(@PathVariable("userId") Long userId) {

        try {
            List<Answer> answers =
                    new ArrayList<>(answerRepository.findByCreatedBy(userId))
                            .stream()
                            .peek(answer -> {
                                        //TODO: replace with service call
                                        long votesCount = 0L;
                                        answer.setVoteCount(votesCount);
                                    }
                            )
                            .collect(Collectors.toList());

            if (answers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(answers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
