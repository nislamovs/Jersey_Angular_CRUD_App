package com.jerseyexample.app.unitTests.controllers;

import com.jerseyexample.app.controller.UserResource;
import com.jerseyexample.app.domain.exceptions.UserNotFoundException;
import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.domain.responses.UserCreatedResponse;
import com.jerseyexample.app.domain.responses.UserDetailsResponse;
import com.jerseyexample.app.domain.responses.UserResponse;
import com.jerseyexample.app.model.UserEntity;
import com.jerseyexample.app.services.UserService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.jerseyexample.app.testHelperClasses.UserTestDataFactory.makeUserList;
import static com.jerseyexample.app.testHelperClasses.UserTestDataFactory.newUserEntity;
import static com.jerseyexample.app.testHelperClasses.UserTestDataFactory.newUserRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTests {

    @InjectMocks
    UserResource userResource = new UserResource();

    @Mock
    UserService userService;

    @Test
    @DisplayName("Retrieving page of users")
    public void shouldRetrieveUsersPage() {
        Page<UserEntity> userList = new PageImpl<>(makeUserList());
        when(userService.findAllUsers(5, 1, "asc", "id")).thenReturn(userList);

        Response response = userResource.getPageOfUsers(5, 1, "asc", "id");
        List<UserResponse> userResponse = (List<UserResponse>) response.getEntity();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(userList.getContent().get(0).getFirstname(), userResponse.get(0).getFirstname());
        assertEquals(userList.getContent().get(2).getAddress(), userResponse.get(2).getAddress());
    }

    @Test
    @DisplayName("Creating new user")
    public void shouldCreateNewUser() {
        UserRequest userRequest = newUserRequest();
        UserEntity userEntity = newUserEntity();
        when(userService.createUser(userRequest)).thenReturn(userEntity);

        Response response = userResource.createUser(userRequest);
        UserCreatedResponse userResponse = (UserCreatedResponse) response.getEntity();

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(userRequest.getFirstname(), userResponse.getFirstname());
        assertEquals(userRequest.getLastname(), userResponse.getLastname());
        assertEquals(userRequest.getEmail(), userResponse.getEmail());
    }

    @Test(expected = ValidationException.class)
    @DisplayName("Creating new user with empty firstname")
    public void shouldFailCreatingNewUser_FirstnameEmpty() {
        UserRequest userRequest = newUserRequest();
        userRequest.setFirstname(null);
        when(userService.createUser(userRequest)).thenThrow(new ValidationException());

        Response response = userResource.createUser(userRequest);
    }

    @Test
    @DisplayName("Retrieving new user by id")
    public void shouldRetrieveUserById() {
        UserEntity userEntity = newUserEntity();
        when(userService.findById(1L)).thenReturn(userEntity);

        Response response = userResource.getUserById(1L);
        UserDetailsResponse userResponse = (UserDetailsResponse) response.getEntity();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(userEntity.getFirstname(), userResponse.getFirstname());
        assertEquals(userEntity.getLastname(), userResponse.getLastname());
        assertEquals(userEntity.getEmail(), userResponse.getEmail());
    }

    @Test(expected=UserNotFoundException.class)
    @DisplayName("Retrieving new user by invalid id")
    public void shouldFailRetrievingUserById_WrongId() throws UserNotFoundException {
        UserEntity userEntity = newUserEntity();
        when(userService.findById(1000L)).thenThrow(new UserNotFoundException("User Not found"));

        Response response = userResource.getUserById(1000L);
    }

}
