package com.springboot.security.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title= "Spring Boot Open API Test with Swagger",
        description = "Spring Boot Open API 명세",
        version = "v1",
        termsOfService = "http://www.tg360tech.com/terms",
        license = @License(
                name = "Apache License Version 2.0",
                url = "http://www.apache.org/licenses/LICENSE-2.0"
        ),
        contact = @Contact(
                name = "dev",
                email = "dev@tg360tech.com"
        )),
        tags = {
                @Tag(name = "01.GET", description = "GET 기능"),
                @Tag(name = "02.POST", description = "POST 기능"),
                @Tag(name = "03.PUT", description = "PUT 기능"),
                @Tag(name = "04.DELETE", description = "DELETE 기능"),
                @Tag(name = "05.Undefined", description = "미정의 기능")
        },
        security = { @SecurityRequirement(name = "X-Auth-Token"),}
)
@SecuritySchemes({
        @SecurityScheme(name = "X-Auth-Token",
                type = SecuritySchemeType.APIKEY,
                description = "Api token",
                in = SecuritySchemeIn.HEADER,
                paramName = "X-Auth-Token"),
})
@Configuration
public class SwaggerConfig {

}
