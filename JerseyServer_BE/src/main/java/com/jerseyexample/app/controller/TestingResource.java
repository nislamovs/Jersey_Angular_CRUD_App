package com.jerseyexample.app.controller;

import com.jerseyexample.app.domain.exceptions.UserNotFoundException;
import com.jerseyexample.app.model.UserEntity;
import com.jerseyexample.app.repository.UserRepository;
import com.jerseyexample.app.services.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Component
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
