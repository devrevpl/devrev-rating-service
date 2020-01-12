package pl.devrev.ratingservice.configuration.jackson;

import pl.devrev.ratingservice.domain.Movie;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class MovieDeserializer extends JsonDeserializer<Movie> {

    @Override
    public Movie deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        try {
            return new Movie(jsonParser.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}