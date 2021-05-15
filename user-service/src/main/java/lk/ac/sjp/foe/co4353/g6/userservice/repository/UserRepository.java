package lk.ac.sjp.foe.co4353.g6.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lk.ac.sjp.foe.co4353.g6.userservice.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(long userId);

    List<User> findAll();

    @Query("SELECT new lk.ac.sjp.foe.co4353.g6.userservice.model.Reputation(u.userId, u.reputation) FROM User u where u.userId = :userId")
    Reputation findReputationByUserId(@Param("userId") long userId);
}
