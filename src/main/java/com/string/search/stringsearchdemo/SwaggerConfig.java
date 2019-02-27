/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.string.search.stringsearchdemo;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author aldo.lushkja
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any()) // filter by package
                .paths(PathSelectors.any())            // filter by path selectors
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo(){
        return new ApiInfo(
        "My Rest API for String Search",
        "This is a custom documentation",
                "API TOS",
                "Terms of Service",
                new Contact("Aldo Lushkja", "www.alushkja.me", "aldo.lushkja@gmail.com"),
                "Licence API",
                "License API Url",
                Collections.emptyList());
    }
    
}
