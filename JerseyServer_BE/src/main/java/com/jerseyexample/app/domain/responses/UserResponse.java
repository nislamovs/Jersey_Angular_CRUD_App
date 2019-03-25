package com.jerseyexample.app.domain.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ApiModel
public class UserResponse implements Serializable {

    @ApiModelProperty(required = true, value = "User id", example = "1")
    private Long id;

    @ApiModelProperty(required = true, value = "User name", example = "John")
    private String firstname;

    @ApiModelProperty(required = true, value = "User surname", example = "Dillinger")
    private String lastname;

    @ApiModelProperty(required = true, value = "User access ", example = "Wall street 12")
    private String address;

    @ApiModelProperty(required = true, value = "User email", example = "user123@gmail.com")
    private String email;

    @ApiModelProperty(required = true, value = "User phone", example = "3456783489")
    private String phone;

    @ApiModelProperty(required = true, value = "User photo in png format")
    private byte[] photoImage;

    @ApiModelProperty(required = true, value = "Creation date of current user")
    private Date createdDate;

    @ApiModelProperty(required = true, value = "Last modification date of current user")
    private Date modifiedDate;
}
