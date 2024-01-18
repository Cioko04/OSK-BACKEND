package com.example.osk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.Objects;

@SpringBootApplication
public class OskApplication {

    public static void main(String[] args) {

        SpringApplication.run(OskApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(JdbcTemplate jdbcTemplate) {
        return args -> {
            ClassPathResource resource = new ClassPathResource("dbPatches/INSERT_ALL_CATEGORIES.SQL");

            ResourceDatabasePopulator populator = new ResourceDatabasePopulator(resource);
            populator.execute(Objects.requireNonNull(jdbcTemplate.getDataSource()));
        };
    }

}
