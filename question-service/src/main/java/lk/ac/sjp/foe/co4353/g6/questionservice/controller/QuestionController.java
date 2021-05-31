package lk.ac.sjp.foe.co4353.g6.questionservice.controller;

import lk.ac.sjp.foe.co4353.g6.questionservice.model.Question;
import lk.ac.sjp.foe.co4353.g6.questionservice.repository.QuestionRepository;
import lk.ac.sjp.foe.co4353.g6.questionservice.service.AnswerService;
import lk.ac.sjp.foe.co4353.g6.questionservice.service.VoteService;
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
    final VoteService voteService;

    QuestionController(QuestionRepository questionRepository, AnswerService answerService, VoteService voteService) {
        this.questionRepository = questionRepository;
        this.answerService = answerService;
        this.voteService = voteService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Question>> readAllQuestions() {

        try {
            List<Question> questions = new ArrayList<>(questionRepository.findAll());
            Map<Long, Long> questionIdAnswerCountsMap = answerService.getAnswerCounts(
                            questions.stream()
                                    .map(Question::getQuestionId)
                                    .collect(Collectors.toList())
                    );
            Map<Long, Long> questionIdVotesCountsMap = voteService.getVoteCounts(
                            questions.stream()
                                    .map(Question::getQuestionId)
                                    .collect(Collectors.toList())
                    );

            questions = questions.stream()
                    .peek(question -> {
                        long answersCount = questionIdAnswerCountsMap.get(question.getQuestionId());
                        long votesCount = questionIdVotesCountsMap.get(question.getQuestionId());
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
                        long answersCount = answerService.getAnswerCounts(questionId);
                        long votesCount = voteService.getVoteCounts(questionId);
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
                    question.getCreatedBy(),
                    question.getLastModifiedBy(),
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
                        _questionEntry.setLastModifiedBy(question.getLastModifiedBy());
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
                    new ArrayList<>(questionRepository.findByCreatedBy(userId));
            Map<Long, Long> questionIdAnswerCountsMap = answerService.getAnswerCounts(
                    questions.stream()
                            .map(Question::getQuestionId)
                            .collect(Collectors.toList())
            );
            Map<Long, Long> questionIdVoteCountsMap = voteService.getVoteCounts(
                    questions.stream()
                            .map(Question::getQuestionId)
                            .collect(Collectors.toList())
            );
            questions = questions
                    .stream()
                    .peek(question -> {
                                long answersCount = questionIdAnswerCountsMap.get(question.getQuestionId());
                                long votesCount = questionIdVoteCountsMap.get(question.getQuestionId());
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
