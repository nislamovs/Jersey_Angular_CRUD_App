package com.jerseyexample.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Configuration
@EnableSwagger2
@Primary
public class SwaggerConfiguration implements SwaggerResourcesProvider {

//    Finally get this shit working thanks to this link:
//    https://stackoverflow.com/questions/32877898/springfox-not-finding-jax-rs-endpoints

    @Resource
    private InMemorySwaggerResourcesProvider inMemorySwaggerResourcesProvider;

    @Override
    public List<SwaggerResource> get() {

        SwaggerResource jerseySwaggerResource = new SwaggerResource();
        jerseySwaggerResource.setLocation("/api/swagger.json");
        jerseySwaggerResource.setSwaggerVersion("2.0");
        jerseySwaggerResource.setName("Jersey");

        return Stream.concat(Stream.of(jerseySwaggerResource), inMemorySwaggerResourcesProvider.get().stream()).collect(Collectors.toList());
    }
}
