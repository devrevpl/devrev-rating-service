package pl.devrev.ratingservice.domain;

import pl.devrev.ratingservice.configuration.jackson.GenreDeserializer;
import pl.devrev.ratingservice.configuration.jackson.ReleaseDateDeserializer;
import pl.devrev.ratingservice.configuration.jackson.ReleaseDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String title;
    @JsonSerialize(using = ReleaseDateSerializer.class)
    @JsonDeserialize(using = ReleaseDateDeserializer.class)
    private ZonedDateTime releaseDate;
    @DBRef
    @JsonDeserialize(using = GenreDeserializer.class)
    private Genre genre;

    public Movie(String id) {
        this.id = id;
    }
}
