package com.jerseyexample.app.domain.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class CreateUserForm {

    @ApiModelProperty(value = "firstname", required = true, name = "User firstname")
    @NotBlank(message = "Firstname cannot be empty.")
    @Min(value = 3, message = "Firstname length should be min 3 and max 50 signs long.")
    @Max(value = 50, message = "Firstname length should be min 3 and max 50 signs long.")
    @FormDataParam("firstname")
    String firstname;

    @ApiModelProperty(value = "lastname", required = true, name = "User lastname")
    @NotBlank(message = "Lastname cannot be empty.")
    @Min(value = 3, message = "Lastname length should be min 3 and max 50 signs long.")
    @Max(value = 50, message = "Lastname length should be min 3 and max 50 signs long.")
    @FormDataParam("lastname")
    String lastname;

    @ApiModelProperty(value = "email", required = true, name = "User email")
    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Email value doesn't fit into email regex pattern.", regexp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
    @FormDataParam("email")
    String email;

    @ApiModelProperty(value = "address", required = true, name = "User address")
    @NotBlank(message = "Address cannot be empty.")
    @Min(value = 3, message = "Address length should be min 3 and max 50 signs long.")
    @Max(value = 50, message = "Address length should be min 3 and max 50 signs long.")
    @FormDataParam("address")
    String address;

    @ApiModelProperty(value = "phone", required = true, name = "User phone")
    @NotBlank(message = "Phone cannot be empty.")
    @Min(value = 3, message = "Phone length should be min 3 and max 50 signs long.")
    @Max(value = 50, message = "Phone length should be min 3 and max 50 signs long.")
    @FormDataParam("phone")
    String phone;

    @ApiModelProperty(value = "birthdate", required = true, name = "User birthdate", example = "2016-01-01")
    @NotBlank(message = "Birthdate cannot be empty.")
    @FormDataParam("birthdate")
    String birthdate;

//Find comment in FE project with content 'multipart_base64_content' and uncomment it.
//  @ApiModelProperty(value = "photo", required = true, name = "User photo")
//  @FormDataParam("photo") InputStream base64Stream,

    @ApiModelProperty(value = "image details", required = true, name = "User image details")
    @FormDataParam("image")
    FormDataContentDisposition fileDetail;

    @ApiModelProperty(value = "image", required = true, name = "User image")
//    @NotBlank(message = "User's photo cannot be empty.")
    @FormDataParam("image")
    InputStream userPhotoStream;

    @ApiModelProperty(value = "description", required = true, name = "User description")
    @Max(value = 500, message = "Description length should be max 500 signs long.")
    @FormDataParam("description")
    String description;

    @ApiModelProperty(value = "skills", required = true, name = "User skills")
    @Max(value = 500, message = "Skills length should be max 500 signs long.")
    @FormDataParam("skills")
    String skills;

    @ApiModelProperty(value = "experience", required = true, name = "User experience")
    @Max(value = 500, message = "Experience length should be max 500 signs long.")
    @FormDataParam("experience")
    String experience;

    public Map<String, Object> toMap() throws IllegalAccessException {
        Map<String, Object> resultMap = new HashMap<>();

        for (Field field : this.getClass().getDeclaredFields()) {
            resultMap.put(field.getName(), field.get(this));
        }
        return resultMap;
    }
}
