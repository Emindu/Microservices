package lk.ac.sjp.foe.co4353.g6.zuulgateway;

import lk.ac.sjp.foe.co4353.g6.zuulgateway.model.AuthenticationRequest;
import lk.ac.sjp.foe.co4353.g6.zuulgateway.model.AuthenticationResponse;
import lk.ac.sjp.foe.co4353.g6.zuulgateway.model.User;
import lk.ac.sjp.foe.co4353.g6.zuulgateway.model.UserJwtPayload;
import lk.ac.sjp.foe.co4353.g6.zuulgateway.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class Controller {

    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsService userDetailsService;

    private final JWTTokenUtil jwtTokenUtil;

    public Controller(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JWTTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect user name or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    @PostMapping("user")
    public ResponseEntity<User> user(@RequestBody UserJwtPayload userJwtPayload){
        final String username = jwtTokenUtil.extractUsername(userJwtPayload.getJwt());
        return ResponseEntity.ok(new User(username));
    }


}