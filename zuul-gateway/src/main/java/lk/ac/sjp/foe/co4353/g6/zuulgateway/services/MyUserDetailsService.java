package lk.ac.sjp.foe.co4353.g6.zuulgateway.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //todo: tmp remove
        return Stream
                .of(
                        new User("1", "foo", new ArrayList<>()),
                        new User("2", "foo", new ArrayList<>()),
                        new User("3", "foo", new ArrayList<>())
                )
                .filter(user -> user.getUsername().equals(userName))
                .findFirst()
                .orElse(null);
    }
}
