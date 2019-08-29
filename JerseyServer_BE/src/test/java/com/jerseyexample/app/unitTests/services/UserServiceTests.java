package com.jerseyexample.app.unitTests.services;

import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.model.UserEntity;
import com.jerseyexample.app.repository.UserRepository;
import com.jerseyexample.app.services.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.jerseyexample.app.testHelperClasses.UserTestDataFactory.makeUserList;
import static com.jerseyexample.app.testHelperClasses.UserTestDataFactory.newUserEntity;
import static com.jerseyexample.app.testHelperClasses.UserTestDataFactory.newUserRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @InjectMocks
    UserServiceImpl userService = new UserServiceImpl();

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldRetrieveAllUsers() {
        List<UserEntity> userEntities = makeUserList();

        when(userRepository.findAll()).thenReturn(userEntities);
        List<UserEntity> actualUsers = userService.findAllUsers();

        for (int i = 0; i < userEntities.size(); i++)
            assertEquals(actualUsers.get(i), userEntities.get(i));
    }

    @Test
    public void shouldRetrieveUserById() {
        UserEntity userEntity = newUserEntity();
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        UserEntity actualUsers = userService.findById(1L);

        assertEquals(actualUsers, userEntity);
        assertEquals(actualUsers.getFirstname(), userEntity.getFirstname());
    }

    @Test
    public void shouldCreateNewUser() throws IOException {
        UserEntity userEntity = newUserEntity();
        userEntity.setId(null);
        UserRequest userRequest = newUserRequest();

        when(userRepository.save(userEntity)).thenReturn(userEntity);
        userEntity = userService.createUser(userRequest);

        checkDataConsistency(userRequest, userEntity);
    }

    private void checkDataConsistency(UserRequest ur, UserEntity ue) {
        assertEquals(ur.getEmail(),      ue.getEmail());
        assertEquals(ur.getFirstname(),  ue.getFirstname());
        assertEquals(ur.getLastname(),   ue.getLastname());
        assertEquals(ur.getAddress(),    ue.getAddress());
        assertEquals(ur.getPhone(),      ue.getPhone());
    }
}
