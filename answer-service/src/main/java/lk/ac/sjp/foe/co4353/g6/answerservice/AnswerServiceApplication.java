package lk.ac.sjp.foe.co4353.g6.answerservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class AnswerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnswerServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner init(AnswerRepository repository) {
        return args -> Stream.of(
                new Answer(
                        1L,
                        "A first Answer",
                        1L,
                        1L,
                        new Date(),
                        new Date()
                ),
                new Answer(
                        1L,
                        "A second answer",
                        1L,
                        1L,
                        new Date(),
                        new Date()
                )
        ).forEach(repository::save);
    }
}

@Entity
@Table(name = "answers")
class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long answerId;

    @Column(name = "question_id")
    private long questionId;

    @Column(name = "text")
    private String text;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Transient
    private Long voteCount;

    public Answer() {
    }

    public Answer(long questionId, String text, Long createdBy, Long lastModifiedBy, Date createdDate, Date lastModifiedDate) {
        this.questionId = questionId;
        this.text = text;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}

@Repository
interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findByAnswerId(Long answerId);
    List<Answer> findByQuestionId(Long questionId);
    List<Answer> findByCreatedBy(Long questionId);
    Long countByQuestionId(Long questionId);
}

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/answers")
class AnswerController {
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


    @GetMapping("/questions/count")
    public ResponseEntity<Map<Long, Long>> getAnswersCountsForQuestions(
            @RequestBody List<Long> questionIdList) {

        try {
            Map<Long, Long> questionIdAnswersCountMap = new HashMap<>();
            questionIdList.forEach(questionId -> questionIdAnswersCountMap.put(
                    questionId,
                    answerRepository.countByQuestionId(questionId)
            ));
            return new ResponseEntity<>(questionIdAnswersCountMap, HttpStatus.OK);
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