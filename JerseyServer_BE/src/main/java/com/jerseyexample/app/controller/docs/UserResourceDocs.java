package com.jerseyexample.app.controller.docs;

import com.jerseyexample.app.domain.requests.CreateUserForm;
import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.domain.responses.ErrorResponse;
import com.jerseyexample.app.domain.responses.UserDetailsResponse;
import com.jerseyexample.app.domain.responses.UserResponse;
import io.swagger.annotations.*;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.BeanParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Api(tags = { "User management" },  description = "Api calls for user operations")
public interface UserResourceDocs {

    @ApiOperation(
            httpMethod = "GET",
            notes = "Resource to retrieve all users",
            value = "Get all users.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All users are retrieved.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    Response getAllUsers();

    @ApiOperation(
            httpMethod = "GET",
            notes = "Resource to retrieve part of users",
            value = "Get part of users.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Requested page of users is retrieved.", response = UserDetailsResponse.class),
            @ApiResponse(code = 204, message = "No users found.", response = UserDetailsResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    Response getPageOfUsers(@ApiParam(value="pageSize", required=false, defaultValue = "5") Integer pageSize,
                            @ApiParam(value="pageNumber", required=false, defaultValue = "1") Integer pageNumber,
                            @ApiParam(value="orderDirection", required=false, defaultValue = "asc") String orderDirection,
                            @ApiParam(value="orderBy", required=false, defaultValue = "id") String orderBy);

    @ApiOperation(
            httpMethod = "POST",
            notes = "Resource to create new user",
            value = "Create new user.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    Response createUser(@ApiParam(value = "UserRequest object", required = true, name = "User request") @Valid UserRequest userRequest);

    @ApiOperation(
            httpMethod = "GET",
            notes = "Resource to retrieve user by id",
            value = "Get user by id.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User retrieved.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    Response getUserById(@ApiParam(value = "ID", required = true, name = "User id") @Min(value = 0, message = "Request params are invalid.") long id);

    @ApiOperation(
            httpMethod = "PUT",
            notes = "Resource to update user data",
            value = "Update user data.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    Response updateUser(@ApiParam(value = "UserRequest object", required = true, name = "User request") @Valid UserRequest userReq);

    @ApiOperation(
            httpMethod = "DELETE",
            notes = "Resource to delete user",
            value = "Delete user by id.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User deleted.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class)
    })
    Response deleteUserById(@ApiParam(value = "ID", required = true, name = "User id") @Min(value = 1, message = "Request params are invalid.")  long id);


    @ApiOperation(
            httpMethod = "POST",
            notes = "Resource to add new user using form (multipart)",
            value = "Add new user.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.MULTIPART_FORM_DATA
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All users are retrieved.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class),
            @ApiResponse(code = 415, message = "Dataformat was wrong.", response = ErrorResponse.class)
    })
    Response createUserByForm(@ApiParam(value = "User form object", required = true, name = "User form request")  @BeanParam CreateUserForm userForm) throws IOException;


    @ApiOperation(
            httpMethod = "PUT",
            notes = "Resource to update existing user using form (multipart)",
            value = "Update user.",
            response = UserResponse.class,
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.MULTIPART_FORM_DATA
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All users are retrieved.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Something went wrong.", response = ErrorResponse.class),
            @ApiResponse(code = 415, message = "Dataformat was wrong.", response = ErrorResponse.class)
    })
    Response updateUserByForm(
            @ApiParam(value = "id", required = true, name = "User id")  @FormDataParam("id") String id,
            @ApiParam(value = "firstname", required = true, name = "User firstname")  @FormDataParam("firstname") String firstname,
            @ApiParam(value = "lastname", required = true, name = "User lastname")  @FormDataParam("lastname") String lastname,
            @ApiParam(value = "email", required = true, name = "User email")  @FormDataParam("email") String email,
            @ApiParam(value = "address", required = true, name = "User address")  @FormDataParam("address") String address,
            @ApiParam(value = "phone", required = true, name = "User phone")  @FormDataParam("phone") String phone,
            @ApiParam(value = "birthdate", required = true, name = "User birthdate")  @FormDataParam("birthdate") String birthdate,

            //Find comment in FE project with content 'multipart_base64_content' and uncomment it.
            //@ApiParam(value = "ID", required = true, name = "User photo")  @FormDataParam("photo") InputStream base64Stream,

            @ApiParam(value = "image", required = true, name = "User photo")  @FormDataParam("image") FormDataContentDisposition fileDetail,
            @ApiParam(value = "image", required = true, name = "User photo")  @FormDataParam("image") InputStream userPhotoStream,

            @ApiParam(value = "description", required = true, name = "User description")  @FormDataParam("description") String description,
            @ApiParam(value = "skills", required = true, name = "User skills")  @FormDataParam("skills") String skills,
            @ApiParam(value = "experience", required = true, name = "User experience")  @FormDataParam("experience") String experience

    ) throws IOException;
}
