package pl.devrev.ratingservice.repository;

import pl.devrev.ratingservice.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
