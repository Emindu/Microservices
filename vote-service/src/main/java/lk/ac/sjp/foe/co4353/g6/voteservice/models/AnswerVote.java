package lk.ac.sjp.foe.co4353.g6.voteservice.models;

import javax.persistence.*;

@Entity
@Table(name = "answerVotes")
public class AnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tableId;


    private long  answerId;

    private long userId;

    private int vote;

    public AnswerVote() {
    }

    public AnswerVote(long answerId, long userId, int vote) {
        this.answerId = answerId;
        this.userId = userId;
        this.vote = vote;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
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
