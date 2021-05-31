package lk.ac.sjp.foe.co4353.g6.questionservice.controller;

import lk.ac.sjp.foe.co4353.g6.questionservice.model.Question;
import lk.ac.sjp.foe.co4353.g6.questionservice.repository.QuestionRepository;
import lk.ac.sjp.foe.co4353.g6.questionservice.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/questions")
public class QuestionController {
    final QuestionRepository questionRepository;
    final AnswerService answerService;

    QuestionController(QuestionRepository questionRepository, AnswerService answerService) {
        this.questionRepository = questionRepository;
        this.answerService = answerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Question>> readAllQuestions() {

        try {
            List<Question> questions = new ArrayList<>(questionRepository.findAll());
            Map<Long, Long> questionIdAnswerCountsMap = answerService.getQuestionIdsVotesCounts(
                            questions.stream()
                                    .map(Question::getQuestionId)
                                    .collect(Collectors.toList())
                    ).getBody();

            questions = questions.stream()
                    .peek(question -> {
                        //todo replace with service call
                        long answersCount = questionIdAnswerCountsMap.get(question.getQuestionId());
                        long votesCount = 0L;
                        question.setAnswersCount(answersCount);
                        question.setVotesCount(votesCount);
                    })
                    .collect(Collectors.toList());

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> readSingleQuestion(@PathVariable("questionId") Long questionId) {

        try {
            Optional<Question> questionEntity = questionRepository.findById(questionId);

            return questionEntity
                    .map(question -> {
                        //todo replace with service call
                        long answersCount = 0L;
                        long votesCount = 0L;
                        question.setAnswersCount(answersCount);
                        question.setVotesCount(votesCount);
                        return new ResponseEntity<>(question, HttpStatus.OK);
                    })
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/")
    public ResponseEntity<Question> createSingleQuestion(@RequestBody Question question) {

        try {
            Date currentTime = new Date();
            Question _question = questionRepository.save(new Question(
                    question.getTitle(),
                    question.getText(),
                    1L,        //todo: change this
                    1L,     //todo: change this
                    currentTime,
                    currentTime
            ));
            return new ResponseEntity<>(_question, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{questionId}")
    public ResponseEntity<Question> updateSingleQuestion(@PathVariable("questionId") Long questionId,
                                                         @RequestBody Question question) {

        try {
            Optional<Question> questionEntry = questionRepository.findById(questionId);

            return questionEntry
                    .map(_questionEntry -> {
                        _questionEntry.setTitle(question.getTitle());
                        _questionEntry.setText(question.getText());
                        _questionEntry.setLastModifiedBy(question.getLastModifiedBy()); // todo: check
                        _questionEntry.setLastModifiedDate(new Date());
                        return new ResponseEntity<>(questionRepository.save(_questionEntry), HttpStatus.OK);
                    })
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Question> deleteSingleQuestion(@PathVariable("questionId") Long questionId) {

        try {
            questionRepository.deleteById(questionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Question>> readQuestionsByUser(@PathVariable("userId") Long userId) {

        try {
            List<Question> questions =
                    new ArrayList<>(questionRepository.findByCreatedBy(userId))
                            .stream()
                            .peek(question -> {
                                        //todo replace with service call
                                        long answersCount = 0L;
                                        long votesCount = 0L;
                                        question.setAnswersCount(answersCount);
                                        question.setVotesCount(votesCount);
                                    }
                            )
                            .collect(Collectors.toList());

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
