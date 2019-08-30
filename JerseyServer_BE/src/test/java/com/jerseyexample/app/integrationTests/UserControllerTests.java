package com.jerseyexample.app.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jerseyexample.app.domain.requests.CreateUserForm;
import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.domain.responses.UserDetailsResponse;
import com.jerseyexample.app.domain.responses.UserResponse;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.MediaType;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.jerseyexample.app.testHelperClasses.UserTestDataFactory.newUserForm;
import static com.jerseyexample.app.testHelperClasses.UserTestDataFactory.newUserRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Slf4j
@Tag("integration-test")
public class UserControllerTests extends AbstractIntegrationTest{

    @Test
    @DisplayName("Retrieving page of users with some related data")
    public void shouldRetrievePageOfUsers() {

        given().when().get("users")
                .then().statusCode(HttpStatus.OK.value())
                .body("firstname", hasItems("Aleksandrs", "Peteris", "Vladislavs", "Kenny", "Fred"))
                .body("lastname", hasItems("Collins", "Klava", "Strass", "Jones", "Mazur"));
    }

    @Test
    @DisplayName("Retrieving user by id")
    public void shouldRetrieveUserById() {
        given().when().get("users/4")
                .then().statusCode(HttpStatus.OK.value())
                .body("firstname", equalTo("Kenny"))
                .body("lastname", equalTo("Jones"))
                .body("address", equalTo("2 Court Rd, Port Glasgow PA14 5PR, UK"))
                .body("email", equalTo("Ashamed@gmail.com"));
    }

    @Test
    @DisplayName("Retrieving user by invalid id")
    public void shouldRetrieveUserByInvalidId() {
        given().when().get("users/444")
                .then().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Retrieving users count")
    public void shouldRetrieveUserCount() {
        given().when().get("users/count")
                .then().statusCode(HttpStatus.OK.value())
                .body("usersCount", greaterThan(10));
    }

    @Test
    @DisplayName("Creating new user")
    public void shouldCreateNewUser() {
        UserRequest ue = newUserRequest();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        given().body(ue)
                .when().post("v2/users")
                .then().statusCode(HttpStatus.CREATED.value())
                .body("firstname", equalTo(ue.getFirstname()))
                .body("lastname", equalTo(ue.getLastname()))
                .body("email", equalTo(ue.getEmail()))
                .body("createdDate", startsWith(LocalDate.now().format(formatter).toString()));
    }

    @Test
    @DisplayName("Creating new user with unfilled fields")
    public void shouldCreateNewUser_unfilledFields() {
        UserRequest ue = newUserRequest();
        ue.setFirstname(null);
        ue.setAddress(null);
        given().body(ue)
                .when().post("v2/users")
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Updating existing user")
    public void shouldUpdateExistingUser() {
        UserRequest ue = newUserRequest();
        ue.setId(5L);

        given().body(ue)
                .when().put("v2/users")
                .then().statusCode(HttpStatus.OK.value());

        given().when().get("users/5")
                .then().statusCode(HttpStatus.OK.value())
                .body("firstname", equalTo(ue.getFirstname()))
                .body("lastname", equalTo(ue.getLastname()))
                .body("email", equalTo(ue.getEmail()));
    }

    @Test
    @DisplayName("Deleting user")
    public void shouldDeleteUser() {
        given().when().delete("users/5")
                .then().statusCode(HttpStatus.NO_CONTENT.value());

        given().when().get("users/5")
                .then().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Creating new user [form]")
    public void shouldCreateNewUser_form() throws IllegalAccessException {
        CreateUserForm userForm = newUserForm();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        mapFormToMultipart(given().contentType("multipart/form-data"), userForm)
            .when().post("users")
            .then().statusCode(HttpStatus.CREATED.value())
            .body("firstname", equalTo(userForm.getFirstname()))
            .body("lastname", equalTo(userForm.getLastname()))
            .body("email", equalTo(userForm.getEmail()))
            .body("createdDate", startsWith(LocalDate.now().format(formatter).toString()));
    }

    @Test
    @DisplayName("Updating existing user [form]")
    public void shouldUpdateExistingUser_form() {
        CreateUserForm userForm = newUserForm();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        given().contentType("multipart/form-data")
            .multiPart("id", "5")
            .multiPart("firstname", testFirstname)
            .multiPart("lastname", testLastname)
            .multiPart("address", "asasdasdasd")
            .multiPart("email", testEmail)
            .multiPart("phone", "234234234234")
            .multiPart("birthdate", "1988-11-11")
            .multiPart("image", testImage)
            .multiPart("description", "descr sdsdsdasdasd")
            .multiPart("skills", "skill sdadad")
            .multiPart("experience", "experience ddsfsdfsf")

        .when().put("users")
                .then().statusCode(HttpStatus.OK.value())
                .body("firstname", equalTo(testFirstname))
                .body("lastname", equalTo(testLastname))
                .body("email", equalTo(testEmail))
                .body("createdDate", startsWith(LocalDate.now().format(formatter).toString()));

        given().when().get("users/5").then().statusCode(HttpStatus.OK.value())
                .body("firstname", equalTo(testFirstname))
                .body("lastname", equalTo(testLastname))
                .body("email", equalTo(testEmail));
    }

    private RequestSpecification mapFormToMultipart(RequestSpecification requestPrefix, CreateUserForm userForm) throws IllegalAccessException {
        RequestSpecification prefix = (requestPrefix == null) ? given() : requestPrefix;

        for (Map.Entry<String, Object> entry : userForm.toMap().entrySet()) {
            if (!"fileDetail".equals(entry.getKey()) && !"userPhotoStream".equals(entry.getKey())) {
                prefix = prefix.multiPart(entry.getKey(), String.valueOf(entry.getValue()));
            } else if ("userPhotoStream".equals(entry.getKey())) {
                prefix = prefix.multiPart("image", testImage);
            }
        }

        return prefix;
    }
}
