package com.jerseyexample.app.domain.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
public class UserRequest implements Serializable {

    private Long id;

    @Size(min = 3, max = 50, message = "Firstname length should be min 3 and max 50 signs long.")
    @NotEmpty(message = "Firstname cannot be empty.")
    private String firstname;

    @Size(min = 3, max = 50, message = "Lastname length should be min 3 and max 50 signs long.")
    @NotEmpty(message = "Lastname cannot be empty.")
    private String lastname;

    @Size(min = 3, max = 60, message = "Address length should be min 3 and max 60 signs long.")
    @NotEmpty(message = "Address cannot be empty.")
    private String address;

    @Email(message = "Email value doesn't fit into email regex pattern.")
    @NotEmpty(message = "Email cannot be empty.")
    private String email;

    @Size(min = 7, max = 15, message = "Phone length should be min 7 and max 15 signs long.")
    @NotEmpty(message = "Phone cannot be empty.")
    private String phone;

    @NotEmpty(message = "Photo cannot be empty.")
    @Size(max = 30000, message = "Image size is too big.")
    private byte[] photoImage;
}


