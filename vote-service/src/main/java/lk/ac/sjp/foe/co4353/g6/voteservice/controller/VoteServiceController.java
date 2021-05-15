package lk.ac.sjp.foe.co4353.g6.voteservice.controller;

import lk.ac.sjp.foe.co4353.g6.voteservice.models.AnswerVote;
import lk.ac.sjp.foe.co4353.g6.voteservice.models.QuestionVote;
import lk.ac.sjp.foe.co4353.g6.voteservice.repository.AnswerVotesRepository;
import lk.ac.sjp.foe.co4353.g6.voteservice.repository.QuestionVotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteServiceController {

    @Autowired
    AnswerVotesRepository answerVotesRepository;

    @Autowired
    QuestionVotesRepository questionVotesRepository;


    @GetMapping("/")
    public String gethome(){
        return "hi";
    }

    @GetMapping("/answers/{answerId}")
    public ResponseEntity<Integer> getAnswerVotes(@PathVariable long answerId){
        try {
            List<AnswerVote> answerVote =  answerVotesRepository.findByAnswerId(answerId);

            if (answerVote == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(answerVote.size(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<Integer> getQuestionVotes(@PathVariable long questionId){
        try {
            List<QuestionVote> questionVote =  questionVotesRepository.findByQuestionId(questionId);

            if (questionVote == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(questionVote.size(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/state/user/{userId}/answer/{answerId}")
    public ResponseEntity<String> getAnswerVoteState(@PathVariable long userId, @PathVariable long answerId){
        try {
        AnswerVote answerVote = answerVotesRepository.findByAnswerIdAndUserId(answerId, userId);
        if (answerVote == null){
            return new ResponseEntity<>("No_voted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Already_voted", HttpStatus.OK);
        }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/state/user/{userId}/question/{questionId}")
    public ResponseEntity<String> getQuestionVoteState(@PathVariable long userId, @PathVariable long questionId){
        try {
            QuestionVote questionVote = questionVotesRepository.findByQuestionIdAndUserId(questionId, userId);
            if (questionVote == null){
                return new ResponseEntity<>("No_voted",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Already_voted", HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/vote/user/{userId}/question/{questionId}")
    public ResponseEntity<QuestionVote> setQuestionVote(@PathVariable long userId, @PathVariable long questionId){

        try {
            QuestionVote questionVote = questionVotesRepository.findByQuestionIdAndUserId(questionId, userId);
            if (questionVote == null){
                QuestionVote questionVote2 = questionVotesRepository.save(new QuestionVote(questionId, userId, 1));
                return new ResponseEntity<>(questionVote2, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/vote/user/{userId}/answer/{answerId}")
    public ResponseEntity<AnswerVote> setAnswerVote(@PathVariable long userId, @PathVariable long answerId){

        try {
            AnswerVote answerVote = answerVotesRepository.findByAnswerIdAndUserId(answerId, userId);
            if (answerVote == null){
                AnswerVote answerVote2 = answerVotesRepository.save(new AnswerVote(answerId, userId, 1));
                return new ResponseEntity<>(answerVote2, HttpStatus.CREATED);
            }else{
                //question id and user id already voted
                return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
