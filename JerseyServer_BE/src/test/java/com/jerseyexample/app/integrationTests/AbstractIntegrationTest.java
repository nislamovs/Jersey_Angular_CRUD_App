package com.jerseyexample.app.integrationTests;

import com.jerseyexample.app.JerseydemoApplication;
import com.jerseyexample.app.configuration.JerseyConfiguration;
import com.jerseyexample.app.configuration.SwaggerConfiguration;
import com.jerseyexample.app.controller.UserResource;
import com.jerseyexample.app.testHelperClasses.SSLUtil;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.io.BufferedWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {JerseydemoApplication.class,
        JerseyConfiguration.class,
        UserResource.class
        }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@Tag("integration-test")
public class AbstractIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void presetConnection() {
        RestAssured.port = port;
        initializeTestClass();
    }

//    @Inject
//    private TestRestTemplate restTemplate;

//    @Before
//    public void init() throws Exception {
//        SSLUtil.turnOffSslChecking();
//    }

    @BeforeAll
    public static void setUp() throws Exception {

//        KeyStore keyStore = null;
//        SSLConfig config = null;
//
//        try {
//            keyStore = KeyStore.getInstance("PKCS12");
//            keyStore.load(new FileInputStream("classpath:certificates/JerseyServer_BE.p12"), "66821e0d49db9f867d484087aec80b4f".toCharArray());
//        } catch (Exception ex) {
//            System.out.println("Error while loading keystore >>>>>>>>>");
//            ex.printStackTrace();
//        }
//
//        if (keyStore != null) {
//            SSLSocketFactory clientAuthFactory = new SSLSocketFactory(keyStore, "66821e0d49db9f867d484087aec80b4f");
//            // set the config in rest assured
//            config = new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
//            RestAssured.config = RestAssured.config().sslConfig(config);
//        }
//        try {
//            RestAssured.useRelaxedHTTPSValidation();
//            RestAssured.config().getSSLConfig().with().keyStore("classpath:certificates/JerseyServer_BE.p12", "66821e0d49db9f867d484087aec80b4f");
//        } catch (Exception ex) {
//            System.out.println("Error while loading keystore >>>>>>>>>");
//            ex.printStackTrace();
//        }

//                try {
//            RestAssured.useRelaxedHTTPSValidation("TLS");
//            RestAssured.config().getSSLConfig().with().trustStore("classpath:certificates/JerseyServer_BE.p12", "66821e0d49db9f867d484087aec80b4f");
//        } catch (Exception ex) {
//            System.out.println("Error while loading keystore >>>>>>>>>");
//            ex.printStackTrace();
//        }

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.basePath = "/api/";
    }

    private void initializeTestClass() {
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                setAccept(ContentType.JSON).
                build();//.relaxedHTTPSValidation("TLS").auth().certificate("classpath:certificates/JerseyServer_BE.p12", "66821e0d49db9f867d484087aec80b4f");
    }

//    @Test
//    public void dfsfsdf() throws Exception {
//        ResponseEntity<String> response = restTemplate.getForEntity(
//                new URL(getRootUrl()).toString(), String.class);
//
//        String swaggerJson = response.getBody();
//        Files.createDirectories(Paths.get("build/swagger"));
//        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("build/swagger", "swagger.json"), StandardCharsets.UTF_8)){
//            writer.write(swaggerJson);
//        }
//    }
//
//    private String getRootUrl() {
//        return "https://localhost:" + port + "/api/users";
//    }

}
