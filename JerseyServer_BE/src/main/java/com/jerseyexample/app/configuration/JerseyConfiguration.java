package com.jerseyexample.app.configuration;

import com.jerseyexample.app.controller.TestingResource;
import com.jerseyexample.app.controller.UserResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ApplicationPath("/api")
public class JerseyConfiguration extends ResourceConfig {

    private LoggingFeature JERSEY_LOGGER = new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                                            Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 100000);

    public JerseyConfiguration()
    {
        this.registerEndpoints();
        this.configureSwagger();
    }

    private void registerEndpoints() {
        register(RequestContextFilter.class);
        register(MultiPartFeature.class);
//Necessary to exclude to make possible launch standalone jar
//        packages("com.jerseyexample.app");

        property(ServletProperties.FILTER_FORWARD_ON_404, true);

        register(JERSEY_LOGGER);

        register(UserResource.class);
        register(TestingResource.class);

        // Access through /<Jersey's servlet path>/application.wadl
        register(WadlResource.class);
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setConfigId("spring-boot-jaxrs-swagger");
        swaggerConfig.setSchemes(new String[] { "https" });

        swaggerConfig.setTitle("User API");
        swaggerConfig.setDescription("User management service");
        swaggerConfig.setContact("Nizami Islamovs : nizamiislamovs@gmail.com");
        swaggerConfig.setVersion("1.0.0");

        swaggerConfig.setBasePath("/api");
        swaggerConfig.setResourcePackage("com.jerseyexample.app");
        swaggerConfig.setPrettyPrint(true);
        swaggerConfig.setScan(true);

        SwaggerConfigLocator.getInstance().putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, swaggerConfig);
        // https://localhost:8080/api/swagger.json
    }

 }
