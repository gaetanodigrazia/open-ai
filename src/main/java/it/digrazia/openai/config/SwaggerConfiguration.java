package it.digrazia.openai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfiguration {

    @Value("${server.port}")
    private String applicationPort;
    @Bean
    public OpenAPI openAPI() {
        Server firstMicroservice = new Server();
        firstMicroservice.setDescription("First Microservice");
        firstMicroservice.setUrl("http://localhost:"+applicationPort);

        OpenAPI openAPI = new OpenAPI();
        openAPI.info(new Info()
                .title("Swagger documentation for OpenAI API")
                .description(
                        "Documenting Spring Boot REST API for OpenAI API")
                .version("1.0.0")
                .contact(new Contact().name("Tal dei tali").
                        url("https://google.it").email("test@test.it")));
        ;
        openAPI.setServers(Arrays.asList(firstMicroservice));

        return openAPI;
    }

}
