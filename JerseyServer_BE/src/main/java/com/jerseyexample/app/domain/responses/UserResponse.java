package com.jerseyexample.app.domain.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ApiModel
public class UserResponse {

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

    @ApiModelProperty(required = true, value = "User birthdate", example = "12.04.1966")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime birthdate;

    @ApiModelProperty(required = true, value = "User photo icon in png format")
    private byte[] photoIcon;

    @ApiModelProperty(required = true, value = "Creation date of current user")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss z", timezone = "EET")
    private Date createdDate;

    @ApiModelProperty(required = true, value = "Last modification date of current user")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss z", timezone = "EET")
    private Date modifiedDate;
}
