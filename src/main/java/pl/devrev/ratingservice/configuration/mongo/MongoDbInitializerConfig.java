package pl.devrev.ratingservice.configuration.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
@ConditionalOnProperty(
        value = "moviesdb.populatebyinitdata",
        havingValue = "true")
public class MongoDbInitializerConfig {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean init(
            @Qualifier("objectMapper") ObjectMapper mapper) {

        Jackson2RepositoryPopulatorFactoryBean factory =
                new Jackson2RepositoryPopulatorFactoryBean();
        factory.setMapper(mapper);

        Resource sourceData = new ClassPathResource("data.json");
        factory.setResources(new Resource[]{sourceData});

        return factory;
    }

}
