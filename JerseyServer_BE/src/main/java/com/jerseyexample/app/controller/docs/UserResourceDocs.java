package com.jerseyexample.app.controller.docs;

import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.domain.responses.ErrorResponse;
import com.jerseyexample.app.domain.responses.UserResponse;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;

import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Api(tags = { "User management" },  description = "Api calls for user operations")
public interface UserResourceDocs {

//    @ApiOperation(
//            httpMethod = "GET",
//            notes = "Resource to retrieve all users",
//            value = "Get all users.",
//            response = UserResponse.class,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "All users are retrieved.", response = UserResponse.class),
//            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
//    })
//    Response getAllUsers() throws URISyntaxException;
//
//    @ApiOperation(
//            httpMethod = "GET",
//            notes = "Resource to retrieve part of users",
//            value = "Get part of users.",
//            response = UserResponse.class,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Requested page of users is retrieved.", response = UserResponse.class),
//            @ApiResponse(code = 204, message = "No users found.", response = UserResponse.class),
//            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
//    })
//    Response getPageOfUsers(@ApiParam(value="pageSize", required=false, defaultValue = "5") Integer pageSize,
//                            @ApiParam(value="pageNumber", required=false, defaultValue = "1") Integer pageNumber,
//                            @ApiParam(value="orderDirection", required=false, defaultValue = "asc") String orderDirection,
//                            @ApiParam(value="orderBy", required=false, defaultValue = "id") String orderBy) throws URISyntaxException;
//
//    @ApiOperation(
//            httpMethod = "POST",
//            notes = "Resource to create new user",
//            value = "Create new user.",
//            response = UserResponse.class,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "User created.", response = UserResponse.class),
//            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
//    })
//    Response createUser(@ApiParam(value = "UserRequest object", required = true, name = "User request") UserRequest userRequest) throws URISyntaxException;
//
//    @ApiOperation(
//            httpMethod = "GET",
//            notes = "Resource to retrieve user by id",
//            value = "Get user by id.",
//            response = UserResponse.class,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "User retrieved.", response = UserResponse.class),
//            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
//    })
//    Response getUserById(@ApiParam(value = "ID", required = true, name = "User id") long id) throws URISyntaxException;
//
//    @ApiOperation(
//            httpMethod = "PUT",
//            notes = "Resource to update user data",
//            value = "Update user data.",
//            response = UserResponse.class,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "User updated.", response = UserResponse.class),
//            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
//    })
//    Response updateUser(@ApiParam(value = "UserRequest object", required = true, name = "User request") UserRequest userReq) throws URISyntaxException;
//
//    @ApiOperation(
//            httpMethod = "DELETE",
//            notes = "Resource to delete user",
//            value = "Delete user by id.",
//            response = UserResponse.class,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ApiResponses(value = {
//            @ApiResponse(code = 204, message = "User deleted.", response = UserResponse.class),
//            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
//    })
//    Response deleteUserById(@ApiParam(value = "ID", required = true, name = "User id") long id) throws URISyntaxException;
}
