package lk.ac.sjp.foe.co4353.g6.voteservice.models;

public class VoteState {
    private  int vote;

    public VoteState(int vote) {
        this.vote = vote;
    }

    public VoteState() {
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
