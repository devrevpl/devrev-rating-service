package pl.devrev.ratingservice.dto;

import pl.devrev.ratingservice.configuration.jackson.ReleaseDateDeserializer;
import pl.devrev.ratingservice.configuration.jackson.ReleaseDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    @EqualsAndHashCode.Include
    private String id;
    @EqualsAndHashCode.Include
    private String title;
    @JsonSerialize(using = ReleaseDateSerializer.class)
    @JsonDeserialize(using = ReleaseDateDeserializer.class)
    private ZonedDateTime releaseDate;
    private String genreName;
    private String genreIconUri;
    private List<Integer> ratings;
}
