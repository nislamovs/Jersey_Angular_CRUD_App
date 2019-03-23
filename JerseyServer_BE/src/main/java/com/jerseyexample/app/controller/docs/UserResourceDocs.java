package com.jerseyexample.app.controller.docs;

import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.domain.responses.ErrorResponse;
import com.jerseyexample.app.domain.responses.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;

import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Api(tags = { "User management" },  description = "Api calls for user operations")
public interface UserResourceDocs {

    @ApiOperation(
            httpMethod = "GET",
            notes = "Resource to retrieve all users",
            value = "Get all users.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All users are retrieved.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    Response getAllUsers() throws URISyntaxException;

    Response getPageOfUsers(Integer pageSize, Integer pageNumber, String orderDirection, String orderBy) throws URISyntaxException;

    Response createUser(UserRequest userReq) throws URISyntaxException;

    Response getUserById(long id) throws URISyntaxException;

    Response updateUser(UserRequest userReq) throws URISyntaxException;

    Response deleteUser(long id) throws URISyntaxException;
}
