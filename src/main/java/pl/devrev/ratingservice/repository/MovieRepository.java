package pl.devrev.ratingservice.repository;

import pl.devrev.ratingservice.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MovieRepository extends MongoRepository<Movie, String> {

}
