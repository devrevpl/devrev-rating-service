package pl.devrev.ratingservice.domain;

import pl.devrev.ratingservice.configuration.jackson.MovieDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
    public class MovieRate {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private Integer value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime timestamp;
    @DBRef
    @JsonDeserialize(using = MovieDeserializer.class)
    private Movie movie;
}
