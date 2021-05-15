package lk.ac.sjp.foe.co4353.g6.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lk.ac.sjp.foe.co4353.g6.userservice.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(long userId);
    List<User> findAll();
}
