package pl.devrev.ratingservice.endpoint;

public class MovieNotFoundException extends RuntimeException {

    MovieNotFoundException(String message) {
        super(message);
    }
}
