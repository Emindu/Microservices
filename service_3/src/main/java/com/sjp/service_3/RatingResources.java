package com.sjp.service_3;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResources {

    @RequestMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4 );
    }

    @RequestMapping("users/{userId}")
    public UserRating getuserRatings(@PathVariable("userId") String userId){

        List<Rating> ratings = Arrays.asList(
                new Rating("12324" ,  4),
                new Rating("64578" , 6 )
        );
        return new UserRating(ratings);
    }


}
