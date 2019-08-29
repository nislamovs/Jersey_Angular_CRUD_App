package com.jerseyexample.app.domain.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jerseyexample.app.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ApiModel
public class UserCreatedResponse {

    @ApiModelProperty(required = true, value = "User name", example = "John")
    private String firstname;

    @ApiModelProperty(required = true, value = "User surname", example = "Dillinger")
    private String lastname;

    @ApiModelProperty(required = true, value = "User email", example = "user123@gmail.com")
    private String email;

    @ApiModelProperty(required = true, value = "User creation date/time", example = "11-12-2018 14:32:16")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;
}
