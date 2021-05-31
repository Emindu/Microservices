package lk.ac.sjp.foe.co4353.g6.questionservice.repository;

import lk.ac.sjp.foe.co4353.g6.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCreatedBy(Long createdBy);
}
