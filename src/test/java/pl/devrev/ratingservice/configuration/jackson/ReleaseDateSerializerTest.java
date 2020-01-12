package pl.devrev.ratingservice.configuration.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.mockito.Mockito.verify;

class ReleaseDateSerializerTest {

    @Mock
    private JsonGenerator gen;
    @Mock
    private SerializerProvider serializers;
    private ReleaseDateSerializer releaseDateSerializer = new ReleaseDateSerializer();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldSerializeIntoDate() throws IOException {
        // given
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2019, 11, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        // when
        releaseDateSerializer.serialize(zonedDateTime, gen, serializers);
        // then
        verify(gen).writeString("2019-11-01");
    }

    @Test
    void shouldSerializeNull() throws IOException {
        // given
        // when
        releaseDateSerializer.serialize(null, gen, serializers);
        // then
        verify(gen).writeNull();
    }
}