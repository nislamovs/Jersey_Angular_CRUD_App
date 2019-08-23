package com.jerseyexample.app.services;

import com.jerseyexample.app.domain.exceptions.ImageConversionException;
import com.jerseyexample.app.domain.exceptions.UserNotFoundException;
import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserEntity findById(Long id) throws UserNotFoundException;

    UserEntity findByName(String name) throws UserNotFoundException;

    void saveUser(UserRequest user) throws ImageConversionException;

    void updateUser(UserRequest user) throws UserNotFoundException;

    void deleteUser(Long id) throws UserNotFoundException;

    void deleteAllUsers();

    List<UserEntity> findAllUsers();

    Page<UserEntity> findAllUsers(Integer pageSize, Integer pageNumber, String orderDirection, String orderBy);

    boolean isUserExist(UserRequest user);

    boolean isUserExist(long id);

    long getUsersCount();

}
