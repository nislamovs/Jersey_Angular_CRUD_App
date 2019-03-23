package com.jerseyexample.app.converters;


import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.domain.responses.UserResponse;
import com.jerseyexample.app.model.UserEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    private static final ModelMapper mapper = new ModelMapper();

    public static UserResponse toResponse(UserEntity userEntity) {
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        return mapper.map(userEntity, UserResponse.class);
    }

    public static List<UserResponse> toResponse(List<UserEntity> userEntities) {
        return userEntities.stream().map(userEntity -> toResponse(userEntity)).collect(Collectors.toList());
    }

    public static UserEntity toEntity(UserRequest userRequest) {
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        return mapper.map(userRequest, UserEntity.class);
    }
}
