package lk.ac.sjp.foe.co4353.g6.voteservice.repository;


import lk.ac.sjp.foe.co4353.g6.voteservice.models.AnswerVote;
import lk.ac.sjp.foe.co4353.g6.voteservice.models.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionVotesRepository extends JpaRepository<QuestionVote, Long> {

    List<QuestionVote> findByQuestionId(long questionId);
    QuestionVote findByQuestionIdAndUserId(long answerId, long userId);
    List<QuestionVote> findByUserId(long userId);
    List<QuestionVote> findAll();
}
