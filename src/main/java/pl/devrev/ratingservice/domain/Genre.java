package pl.devrev.ratingservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Genre {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private String iconUri;

    public Genre(String id) {
        this.id = id;
    }
}
