package com.hansol.first;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket apiV1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("apis")
                .select()
                .apis(RequestHandlerSelectors.
                        basePackage("com.hansol.first.controller"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }
}
