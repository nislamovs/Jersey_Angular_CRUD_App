package com.jerseyexample.app.controller;

import com.jerseyexample.app.controller.docs.UserResourceDocs;
import com.jerseyexample.app.converters.UserConverter;
import com.jerseyexample.app.converters.UserDetailsConverter;
import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.model.UserEntity;
import com.jerseyexample.app.services.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

@Component
@Resource
@Slf4j
@Path("/")
//@Api(tags = { "User management" },  description = "Api calls for user operations")
public class UserResource {//implements UserResourceDocs {

    @Inject
    UserService userService;

    @GET
    @Path("/users/all")
    @Produces("application/json")
    @Transactional(readOnly = true)
    public Response getAllUsers() throws URISyntaxException {

        log.info("Retrieving user list...");
        return Response.status(Response.Status.OK)
                .entity(UserConverter.toResponse(userService.findAllUsers())).build();
    }

    @GET
    @Path("/users/count")
    @Produces("application/json")
    public Response getUsersCount() throws URISyntaxException {

        HashMap<String, Long> response = new HashMap<>();
        response.put("usersCount", userService.getUsersCount());

        log.info("Retrieving user count...");
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("/users")
    @Produces("application/json")
    @Transactional(readOnly = true)
    public Response getPageOfUsers(@DefaultValue("5")   @QueryParam("PageSize") Integer pageSize,
                                   @DefaultValue("1")   @QueryParam("PageNumber") Integer pageNumber,
                                   @DefaultValue("asc") @QueryParam("OrderDirection") String orderDirection,
                                   @DefaultValue("id")  @QueryParam("OrderBy") String orderBy) throws URISyntaxException {

        log.info("Retrieving user page [ pageSize : pageNumber : orderBy : orderDirection == " + pageSize + " : " + pageNumber + " : " + orderBy + " : " + orderDirection + " ]");
        Page<UserEntity> users = userService.findAllUsers(pageSize, pageNumber, orderDirection, orderBy);
        long count = userService.getUsersCount();
        log.info("Users found: " + count + "]");

        return ObjectUtils.isEmpty(users) ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.status(Response.Status.OK).header("user-count", String.valueOf(count))
                .entity(UserConverter.toResponse(users.getContent())).build();
    }

    @POST
    @Path("/v2/users")
    @Consumes("application/json")
    @Transactional
    public Response createUser(@Valid UserRequest userRequest) throws URISyntaxException {

        log.info("Creating new user : [" + userRequest.getFirstname() + " " + userRequest.getLastname() + "]");
        userService.saveUser(userRequest);

        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserByForm (
            @FormDataParam("firstname") String firstname,
            @FormDataParam("lastname") String lastname,
            @FormDataParam("email") String email,
            @FormDataParam("address") String address,
            @FormDataParam("phone") String phone,
            @FormDataParam("birthdate") String birthdate,

//Find comment in FE project with content 'multipart_base64_content' and uncomment it.
//            @FormDataParam("photo") InputStream base64Stream,

            @FormDataParam("image") FormDataContentDisposition fileDetail,
            @FormDataParam("image") InputStream userPhotoStream,

            @FormDataParam("description") String description,
            @FormDataParam("skills") String skills,
            @FormDataParam("experience") String experience

    ) throws IOException {

        UserRequest user = UserRequest.builder().firstname(firstname).lastname(lastname)
                .address(address).email(email).phone(phone).birthdate(LocalDate.parse(birthdate).atStartOfDay())
                .photoImage(IOUtils.toByteArray(userPhotoStream))
                .description(description).skills(skills).experience(experience)
                .build();

        userService.saveUser(user);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/users/{id}")
    @Produces("application/json")
    @Transactional(readOnly = true)
    public Response getUserById(@PathParam("id") @Min(value = 0, message = "Request params are invalid.") long id) throws URISyntaxException {

        log.info("Retrieving user by id: [" + id + "]");

        System.out.println(UserDetailsConverter.toResponse(userService.findById(id)));


        return Response.status(Response.Status.OK).entity(UserDetailsConverter.toResponse(userService.findById(id))).build();
    }

    @PUT
    @Path("/users")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/json")
    @Transactional
    public Response updateUserByForm(
            @FormDataParam("id") String id,
            @FormDataParam("firstname") String firstname,
            @FormDataParam("lastname") String lastname,
            @FormDataParam("email") String email,
            @FormDataParam("address") String address,
            @FormDataParam("phone") String phone,
            @FormDataParam("birthdate") String birthdate,

//Find comment in FE project with content 'multipart_base64_content' and uncomment it.
//            @FormDataParam("photo") InputStream base64Stream,

            @FormDataParam("image") FormDataContentDisposition fileDetail,
            @FormDataParam("image") InputStream userPhotoStream,

            @FormDataParam("description") String description,
            @FormDataParam("skills") String skills,
            @FormDataParam("experience") String experience

    ) throws IOException {

        log.info("Updating user by id: [" + id + "]");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        UserRequest user = UserRequest.builder().id(Long.valueOf(id)).firstname(firstname).lastname(lastname)
                .address(address).email(email).phone(phone).birthdate(LocalDate.parse(birthdate, formatter).atStartOfDay())
                .photoImage(IOUtils.toByteArray(userPhotoStream))
                .description(description).skills(skills).experience(experience)
                .build();

        userService.saveUser(user);

        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/v2/users")
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response updateUser(@Valid UserRequest userReq) throws URISyntaxException {

        log.info("Updating user by id: [" + userReq.getId() + "]");
        userService.updateUser(userReq);

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/users/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response deleteUserById(@PathParam("id") @Min(value = 1, message = "Request params are invalid.") long id) throws URISyntaxException {

        log.info("Deleting user by id: [" + id + "]");
        userService.deleteUser(id);

        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
