package com.jerseyexample.app.domain.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
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

    private Long id;

    private String firstname;

    private String lastname;

    private String address;

    private String email;

    private String phone;

    private byte[] photoImage;

    private Date createdDate;

    private Date modifiedDate;
}
