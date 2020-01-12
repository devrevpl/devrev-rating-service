package pl.devrev.ratingservice.configuration.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ReleaseDateDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        try {
            LocalDate localDate = LocalDate.parse(jsonParser.getText(), ReleaseDateSerializer.FORMATTER);
            return localDate.atStartOfDay(ZoneOffset.UTC);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}