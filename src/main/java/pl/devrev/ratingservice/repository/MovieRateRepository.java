package pl.devrev.ratingservice.repository;

import pl.devrev.ratingservice.domain.MovieRate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRateRepository extends MongoRepository<MovieRate, String> {

    List<MovieRate> findByMovieId(String movieId);

}
