package com.example.gamified_habit_tracker_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@OpenAPIDefinition(info = @Info(title = "My API",
        version = "v1",
        description = "This is description"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
@SpringBootApplication
@EnableCaching
public class GamifiedHabitTrackerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamifiedHabitTrackerApiApplication.class, args);
    }

}
