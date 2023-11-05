package com.springboot.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(title= "Test Swagger App",
        description = "Test Swagger app api 명세",
        version = "v1"
        ),
        tags = {
                @Tag(name = "01.GET", description = "GET 기능"),
                @Tag(name = "02.POST", description = "POST 기능"),
                @Tag(name = "03.PUT", description = "PUT 기능"),
                @Tag(name = "04.DELETE", description = "DELETE 기능"),
                @Tag(name = "05.Undefined", description = "미정의 기능")
        }
)
//@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi testSwaggerApi(){
        String[] paths = {"/api/v1/**"};

        return GroupedOpenApi
                .builder()
                .group("TEST SWAGGER API v1")
                .pathsToMatch(paths)
                .build();
    }
/*
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Open API Test with Swagger")
                .description("설명 부분")
                .version("1.0.0")
                .build();
    }*/
}
