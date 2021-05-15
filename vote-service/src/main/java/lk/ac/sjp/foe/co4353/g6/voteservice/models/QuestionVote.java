package lk.ac.sjp.foe.co4353.g6.voteservice.models;


import javax.persistence.*;
import java.util.Date;

//todo: move this to a separate file
@Entity
@Table(name = "questionVotes")
public class QuestionVote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tableId;

    private long questionId;

    private long userId;

    private int vote;

    public QuestionVote() {
    }

    public QuestionVote(long questionId, long userId, int vote) {
        this.questionId = questionId;
        this.userId = userId;
        this.vote = vote;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}