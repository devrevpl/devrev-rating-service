package pl.devrev.ratingservice.endpoint;

import org.springframework.web.bind.annotation.*;
import pl.devrev.ratingservice.domain.Movie;
import pl.devrev.ratingservice.domain.MovieRate;
import pl.devrev.ratingservice.dto.MovieDto;
import pl.devrev.ratingservice.dto.MovieMapper;
import pl.devrev.ratingservice.repository.MovieRateRepository;
import pl.devrev.ratingservice.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api")
public class MovieEndpoint {

    private final MovieRepository movieRepository;
    private final MovieRateRepository movieRateRepository;
    private final MovieMapper movieMapper;

    @RequestMapping(value = "/movies")
    public List<MovieDto> movies() {
        List<Movie> movies = movieRepository.findAll();
        log.info(String.format("Found %s movies in database", movies.size()));
        List<MovieDto> movieDtos = convertToDto(movies);
        attachRatesToMovies(movieDtos);
        return movieDtos;
    }

    private List<MovieDto> convertToDto(List<Movie> movies) {
        return movies.stream().map(movieMapper::mapToMovieDto).collect(Collectors.toList());
    }

    private void attachRatesToMovies(List<MovieDto> movieDtos) {
        for (MovieDto movieDto : movieDtos) {
            List<MovieRate> movieRates = movieRateRepository.findByMovieId(movieDto.getId());
            movieDto.setRatings(movieRates.stream().map(MovieRate::getValue).collect(Collectors.toList()));
        }
    }

    @RequestMapping(value = "/movie/{movieId}/ratings/{newRate}",
            method = RequestMethod.PATCH)
    public void rateMovie(@PathVariable("movieId") String movieId,
                     @Min(1) @Max(10)
                     @PathVariable("newRate") Integer value) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()) {
            log.info(String.format("Adding new rate[%s] for movie: %s", value, movieId));
            addNewRate(movie.get(), value);
        } else {
            throw new MovieNotFoundException("There is no movie with id:" + movieId);
        }
    }

    private void addNewRate(Movie movie, int value) {
        MovieRate movieRate = MovieRate.builder()
                .timestamp(ZonedDateTime.now(ZoneOffset.UTC))
                .value(value)
                .movie(movie)
                .build();
        movieRateRepository.save(movieRate);
    }
}


