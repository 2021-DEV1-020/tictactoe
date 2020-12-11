package com.bnppf.tictactoe.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bnppf.tictactoe.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class);
    }



    private ApiInfo apiInfo() {
        return new ApiInfo("Tic Tac Toe REST API", "API to present webservices of Tic Tac Toe", "V0.1", "TODO",
                new Contact("Contact TODO", "tictactoe.com", "tictactoe@gmail.com"), "me", "me", Collections.emptyList());

    }
}
