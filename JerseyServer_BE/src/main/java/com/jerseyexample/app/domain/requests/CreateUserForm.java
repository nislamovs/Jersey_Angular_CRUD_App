package com.jerseyexample.app.domain.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class CreateUserForm {

    @ApiModelProperty(value = "firstname", required = true, name = "User firstname")
    @FormDataParam("firstname")
    String firstname;

    @ApiModelProperty(value = "lastname", required = true, name = "User lastname")
    @FormDataParam("lastname")
    String lastname;

    @ApiModelProperty(value = "email", required = true, name = "User email")
    @FormDataParam("email")
    String email;

    @ApiModelProperty(value = "address", required = true, name = "User address")
    @FormDataParam("address")
    String address;

    @ApiModelProperty(value = "phone", required = true, name = "User phone")
    @FormDataParam("phone")
    String phone;

    @ApiModelProperty(value = "birthdate", required = true, name = "User birthdate", example = "2016-01-01")
    @FormDataParam("birthdate")
    String birthdate;

//Find comment in FE project with content 'multipart_base64_content' and uncomment it.
//  @ApiModelProperty(value = "photo", required = true, name = "User photo")
//  @FormDataParam("photo") InputStream base64Stream,

    @ApiModelProperty(value = "image details", required = true, name = "User image details")
    @FormDataParam("image")
    FormDataContentDisposition fileDetail;

    @ApiModelProperty(value = "image", required = true, name = "User image")
    @FormDataParam("image")
    InputStream userPhotoStream;

    @ApiModelProperty(value = "description", required = true, name = "User description")
    @FormDataParam("description")
    String description;

    @ApiModelProperty(value = "skills", required = true, name = "User skills")
    @FormDataParam("skills")
    String skills;

    @ApiModelProperty(value = "experience", required = true, name = "User experience")
    @FormDataParam("experience")
    String experience;
}
