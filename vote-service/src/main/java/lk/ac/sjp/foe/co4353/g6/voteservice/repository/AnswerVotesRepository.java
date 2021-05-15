package lk.ac.sjp.foe.co4353.g6.voteservice.repository;

import lk.ac.sjp.foe.co4353.g6.voteservice.models.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerVotesRepository extends JpaRepository<AnswerVote, Long> {

    List<AnswerVote> findByAnswerId(long answerId);
    AnswerVote findByAnswerIdAndUserId(long answerId, long userId);
    List<AnswerVote> findByuserId(long userId);
    List<AnswerVote> findAll();
}
