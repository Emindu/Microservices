package lk.ac.sjp.foe.co4353.g6.answerservice.repository;

import lk.ac.sjp.foe.co4353.g6.answerservice.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findByAnswerId(Long answerId);

    List<Answer> findByQuestionId(Long questionId);

    List<Answer> findByCreatedBy(Long questionId);

    Long countByQuestionId(Long questionId);
}
