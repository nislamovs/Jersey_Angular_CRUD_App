package com.jerseyexample.app.controller;

import com.jerseyexample.app.domain.exceptions.UserNotFoundException;
import com.jerseyexample.app.domain.requests.CreateUserForm;
import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.domain.responses.UserCreatedResponse;
import com.jerseyexample.app.model.UserEntity;
import com.jerseyexample.app.repository.UserRepository;
import com.jerseyexample.app.services.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Resource
@Slf4j
@Path("/")
@Api(tags = { "Testing functionality" },  description = "Api calls for testing operations")
public class TestingResource {

    @Inject
    UserService userService;
    //---------------------------------------------------------------------------------------------------

    //TODO: delete this after testing
    @Inject
    UserRepository userRepository;

    @GET
    @Path("/usertest")
    @Produces("application/json")
    public Response getUserTest() throws URISyntaxException {
        return Response.status(Response.Status.OK).entity(userService.findById(1L)).build();
    }

    @GET
    @Path("/userupdate")
    @Produces("application/json")
    public Response updateUserTest() throws URISyntaxException, UserNotFoundException {

        UserEntity user = userService.findById(1L);
        user.setFirstname(user.getFirstname() + "123");
        userRepository.save(user);

        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/usererror")
    @Produces("application/json")
    public String makeErr() {
        throw new UserNotFoundException("yomayo!");
    }


    //----------------------------------------------------------------------------------------------------
}
