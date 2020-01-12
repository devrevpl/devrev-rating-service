package pl.devrev.ratingservice.configuration.jackson;

import pl.devrev.ratingservice.domain.Genre;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class GenreDeserializer extends JsonDeserializer<Genre> {

    @Override
    public Genre deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        try {
            return new Genre(jsonParser.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}