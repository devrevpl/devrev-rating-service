package pl.devrev.ratingservice.configuration.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReleaseDateDeserializerTest {

    @Mock
    private JsonParser jsonParser;
    @Mock
    private DeserializationContext deserializationContext;
    private ReleaseDateDeserializer releaseDateDeserializer = new ReleaseDateDeserializer();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldDeserializeReleaseDateIntoUTC() throws IOException {
        // given
        when(jsonParser.getText()).thenReturn("2010-11-12");
        // when
        ZonedDateTime zonedDateTime = releaseDateDeserializer.deserialize(jsonParser, deserializationContext);
        // then
        assertEquals("2010-11-12T00:00:00Z", zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    @Test
    void shouldFailOnDeserialization() throws IOException {
        // given
        when(jsonParser.getText()).thenReturn("2010-11-12X00:11");
        // when
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            // when
            releaseDateDeserializer.deserialize(jsonParser, deserializationContext);
        });
    }
}