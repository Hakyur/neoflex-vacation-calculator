package ru.rogotovsky.vacation.calculator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI vacationCalculationOpenApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Vacation pay calculator API")
                                .description("REST API for calculating vacation pay based on average monthly salary " +
                                        "and vacation period or vacation duration")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Rogotovsky Dmitry")
                                                .email("drogotovsky@gmail.com")
                                )
                );
    }
}
