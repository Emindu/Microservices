package lk.ac.sjp.foe.co4353.g6.userservice.controller;

import lk.ac.sjp.foe.co4353.g6.userservice.model.*;
import lk.ac.sjp.foe.co4353.g6.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable long userId){

        try {
            User user =  userRepository.findByUserId(userId);

            if (user == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            List<User> users;
            users = userRepository.findAll();

            if(users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("users")
    public ResponseEntity<List<User>> getUsers(@RequestBody List<Long> userIds){
        try {
            List<User> users;
            users = userRepository.findAllById(userIds);

            if(users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("user")
    public ResponseEntity<User> createUser(@RequestBody User user){

        try {
            User _user = userRepository.save(user);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("reputation/{userId}")
    public ResponseEntity<Reputation> getUserReputation(@PathVariable long userId){

        try {
            Reputation reputation = userRepository.findReputationByUserId(userId);
            return new ResponseEntity<>(reputation, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("reputation/{userId}")
    public ResponseEntity<Reputation> putUserReputation(@PathVariable long userId, @RequestBody Reputation reputation){
        try{
            User userToUpdate = userRepository.getOne(userId);
            userToUpdate.setReputation(reputation.getReputation());
            userRepository.save(userToUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
