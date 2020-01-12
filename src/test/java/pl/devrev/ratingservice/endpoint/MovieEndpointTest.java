package pl.devrev.ratingservice.endpoint;

import pl.devrev.ratingservice.domain.Movie;
import pl.devrev.ratingservice.domain.MovieRate;
import pl.devrev.ratingservice.dto.MovieDto;
import pl.devrev.ratingservice.dto.MovieMapper;
import pl.devrev.ratingservice.repository.MovieRateRepository;
import pl.devrev.ratingservice.repository.MovieRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieEndpointTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private MovieRateRepository movieRateRepository;
    @Spy
    private MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);
    @InjectMocks
    private MovieEndpoint movieEndpoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnMoviesWithRates() {
        // given
        when(movieRepository.findAll()).thenReturn(sampleMovies());
        when(movieRateRepository.findAll()).thenReturn(sampleMovieRates());
        // when
        List<MovieDto> result = movieEndpoint.movies();
        assertEquals(2, result.size());
        // then
        List<MovieDto> expected = Lists.list(
                MovieDto.builder().id("1").title("Title 1").ratings(Lists.list(5, 8)).build(),
                MovieDto.builder().id("2").title("Title 2").build()
        );
        assertEquals(expected.get(0), result.get(0));
        assertEquals(expected.get(1), result.get(1));
    }

    @Test
    void shouldAddNewRate() {
        // given
        when(movieRepository.findById("1")).thenReturn(Optional.of(sampleMovies().get(1)));
        // when
        movieEndpoint.rateMovie("1", 9);
        // then
        MovieRate expectedMovieRate = MovieRate.builder().value(9).movie(sampleMovies().get(1)).build();
        verify(movieRateRepository, times(1)).save(expectedMovieRate);
    }

    @Test
    void shouldNotAddRateForNotExistingMovie() {
        // given
        when(movieRepository.findById("1")).thenReturn(Optional.of(sampleMovies().get(1)));
        // then
        Assertions.assertThrows(MovieNotFoundException.class, () -> {
            // when
            movieEndpoint.rateMovie("5", 9);
        });
    }

    private List<Movie> sampleMovies() {
        Movie movie1 = Movie.builder().id("1").title("Title 1").releaseDate(ZonedDateTime.now()).build();
        Movie movie2 = Movie.builder().id("2").title("Title 2").releaseDate(ZonedDateTime.now()).build();
        return Lists.list(movie1, movie2);
    }

    private List<MovieRate> sampleMovieRates() {
        MovieRate movieRate1 = MovieRate.builder().id("1").value(5).movie(sampleMovies().get(1)).build();
        MovieRate movieRate2 = MovieRate.builder().id("1").value(8).movie(sampleMovies().get(1)).build();
        return Lists.list(movieRate1, movieRate2);
    }
}