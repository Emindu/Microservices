package lk.ac.sjp.foe.co4353.g6.questionservice;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class QuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionServiceApplication.class, args);
    }

    // Temporary: add some dummy entries to table at init
    // todo: remove this and replace with a data.sql file
    @Bean
    ApplicationRunner init(QuestionRepository repository) {
        return args -> Stream.of(
                new Question(
                        "What are the differences between a pointer variable and a reference variable in C++?",
                        "I know references are syntactic sugar, so code is easier to read and write.\n\nBut what are the differences?",
                        1L,
                        1L,
                        new Date(),
                        new Date()
                ),
                new Question(
                        "What is the difference between public, protected, package-private and private in Java?",
                        "In Java, are there clear rules on when to use each of access modifiers, namely the default (package private), public, protected and private, while making class and interface and dealing with inheritance?",
                        1L,
                        1L,
                        new Date(),
                        new Date()
                )
        ).forEach(repository::save);
    }
}

//todo: move this to a separate file
@Entity
@Table(name = "questions")
class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    //todo: replace with a User DTO
    @Column(name = "created_by")
    private Long createdBy;

    //todo: replace with a User DTO
    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    public Question(String title, String text, Long createdBy, Long lastModifiedBy, Date createdDate, Date lastModifiedDate) {
        this.title = title;
        this.text = text;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Question() {
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long id) {
        this.questionId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}

//todo: move this to a separate file
@Repository
interface QuestionRepository extends JpaRepository<Question, Long>{
    List<Question> findByCreatedBy(Long createdBy);
}

//todo: move this to a separate file
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
class QuestionController {
    final QuestionRepository questionRepository;

    QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Question>> readAllQuestions(){

        try {
            List<Question> questions = new ArrayList<>(questionRepository.findAll());

            if(questions.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> readSingleQuestion(@PathVariable("questionId") Long questionId){

        try {
            Optional<Question> question = questionRepository.findById(questionId);

            return question
                    .map(tutorial -> new ResponseEntity<>(tutorial, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/")
    public ResponseEntity<Question> createSingleQuestion(@RequestBody Question question){

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
                                                         @RequestBody Question question){

        try {
            Optional<Question> questionEntry = questionRepository.findById(questionId);

            return questionEntry
                    .map(_questionEntry -> {
                        _questionEntry.setTitle(question.getTitle());
                        _questionEntry.setText(question.getText());
                        _questionEntry.setLastModifiedBy(question.getLastModifiedBy()); // todo: check
                        _questionEntry.setLastModifiedDate(new Date());
                        return new ResponseEntity<>(questionRepository.save(_questionEntry),HttpStatus.OK);
                    })
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Question> deleteSingleQuestion(@PathVariable("questionId") Long questionId){

        try {
            questionRepository.deleteById(questionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Question>> readQuestionsByUser(@PathVariable("userId") Long userId){

        try {
            List<Question> questions = new ArrayList<>(questionRepository.findByCreatedBy(userId));

            if(questions.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}