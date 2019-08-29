package com.jerseyexample.app.integrationTests;

import io.restassured.RestAssured;
import io.restassured.authentication.CertificateAuthSettings;
import io.restassured.builder.RequestSpecBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@Slf4j
@Tag("integration-test")
public class UserControllerTests {


    @Test
    @DisplayName("Retrieving page of users with some related data")
    public void shouldRetrievePageOfUsers() {


//        given().
//                spec(new RequestSpecBuilder()
//                .setAuth(RestAssured
//                                .config().getSSLConfig().with().trustStore("classpath:certificates/JerseyServer_BE.p12", "66821e0d49db9f867d484087aec80b4f").se
//                                CertificateAuthSettings
//                                        .certAuthSettings()
//                                        .keyStoreType("pkcs12")
//                                        .trustStoreType("pkcs12"))).build())


        given().
                spec(new RequestSpecBuilder()
                .setAuth(RestAssured
                        .certificate(
                                "certificates/JerseyServer_BE.p12",
                                "66821e0d49db9f867d484087aec80b4f",
                                "certificates/JerseyServer_BE.p12",
                                "66821e0d49db9f867d484087aec80b4f",
                                CertificateAuthSettings
                                        .certAuthSettings()
                                        .keyStoreType("pkcs12")
                                        .trustStoreType("pkcs12"))).build()).

                log().all().get("api/users")
                .then().statusCode(HttpStatus.OK.value());
//                .body("firstname", hasItems("Aleksandrs", "Peteris", "Vladislavs", "Kenny", "Fred"))
//                .body("lastname", hasItems("Collins", "Klava", "Strass", "Jones", "Mazur"));

    }

}
