package com.gouravThakur.rest.webservices.demoApp.swaggerDocs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@EnableSwagger2
@Configuration
public class SwaggerConfig  {

    public static final Contact contact = new Contact("Gourav Thakur",
            "http://www.in30minutes.com","gt09718@gmail.com");

    public static final ApiInfo Default_Api_Info =new ApiInfo("My api",
            "Awesome api docs", "1.0","urn:tos",
            "contact","Apache2.0","http://www.api-org.com");

    private static final Set<String> Default_Producers_And_Consumes = new HashSet<>(Arrays.
            asList("application/xml", "application/json"));

    @Bean(name = "swagger ")
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(Default_Api_Info)
                .produces(Default_Producers_And_Consumes)
                .consumes(Default_Producers_And_Consumes);
    }


}
