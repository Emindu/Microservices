package lk.ac.sjp.foe.co4353.g6.userservice.model;

import javax.persistence.*;

@Entity
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(name="name")
    private String name;

    @Column(name="country")
    private String country;

    @Column(name="reputation")
    private int reputation;

    public User(){

    }

    public User(String name, String country, int reputation) {
        this.name = name;
        this.country = country;
        this.reputation = reputation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) { this.country = country; }

    public int getReputation() { return reputation; }

    public void setReputation(int reputation){ this.reputation = reputation; }
}
