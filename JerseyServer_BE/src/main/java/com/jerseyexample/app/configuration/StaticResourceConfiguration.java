package com.jerseyexample.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.ws.rs.ext.Provider;

@Configuration
@Provider
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");

//        registry.addResourceHandler("**/BOOT-INF/classes")
//                .addResourceLocations("./classpath:/build/classes/");

        registry.addResourceHandler("images/**")
                .addResourceLocations("./classpath:static/images/");
    }
}