package com.jerseyexample.app.swagger;


import com.jerseyexample.app.JerseydemoApplication;
import com.jerseyexample.app.configuration.JerseyConfiguration;
import com.jerseyexample.app.configuration.SwaggerConfiguration;
import com.jerseyexample.app.testHelperClasses.SSLUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.io.BufferedWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JerseydemoApplication.class, SwaggerConfiguration.class, JerseyConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class Swagger2MarkupTest {

    @Inject
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Before
    public void init() throws Exception {
        SSLUtil.turnOffSslChecking();
    }

    @Test
    public void downloadSwaggerJson() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL(getRootUrl()).toString(), String.class);

        String swaggerJson = response.getBody();
        Files.createDirectories(Paths.get("build/swagger"));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("build/swagger", "swagger.json"), StandardCharsets.UTF_8)){
            writer.write(swaggerJson);
        }
    }

    private String getRootUrl() {
        return "https://localhost:" + port + "/api/swagger.json";
    }

    @After
    public void restore() throws Exception {
        SSLUtil.turnOnSslChecking();
    }

}
