package com.sjp.service_1;

import com.sjp.service_1.beans.CatelogItem;
import com.sjp.service_1.beans.Movie;
import com.sjp.service_1.beans.Rating;
import com.sjp.service_1.beans.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatelogeResources {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder  webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatelogItem> getCatelog(@PathVariable("userId") String userId){


        //get all rated movie IDs
        //for each movie ID, call movie info service and get details

//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 3 )
//        );

        UserRating ratings = restTemplate.getForObject("http://RATING-INFO/ratingsdata/users/" + userId, UserRating.class);

        return ratings.getRatings().stream().map(rating -> {
            //depreacted

            //Movie movie = restTemplate.getForObject("http://localhost:8085/movies/"+ rating.getMovieId(), Movie.class);
            Movie movie = webClientBuilder.build().get()
                    .uri("http://MOVIE-INFO/movies/"+ rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

            return new CatelogItem(movie.getName(), "Desc", rating.getRating());

        }).collect(Collectors.toList());


    }
}
