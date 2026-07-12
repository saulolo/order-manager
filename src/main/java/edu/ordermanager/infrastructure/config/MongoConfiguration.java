package edu.ordermanager.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile("mongo")   // ← Solo activo con perfil mongo
@EnableMongoRepositories(
        basePackages = "edu.ordermanager.infrastructure.adapter.out.persistence.mongo.repository"
)
public class MongoConfiguration {

}