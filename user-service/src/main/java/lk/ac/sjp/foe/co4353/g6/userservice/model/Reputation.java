package lk.ac.sjp.foe.co4353.g6.userservice.model;

public class Reputation{

    long userId;
    int reputation;

    public Reputation(long userId, int reputation){
        this.userId = userId;
        this.reputation = reputation;
    }

    public long getUserId(){return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public int getReputation() { return reputation; }
    public void setReputation(int reputation) { this.reputation = reputation; }
}