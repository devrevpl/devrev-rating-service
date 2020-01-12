package pl.devrev.ratingservice.dto;

import pl.devrev.ratingservice.domain.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(source = "genre.name", target = "genreName")
    @Mapping(source = "genre.iconUri", target = "genreIconUri")
    @Mapping(target = "ratings", ignore = true)
    MovieDto mapToMovieDto(Movie movie);
}
