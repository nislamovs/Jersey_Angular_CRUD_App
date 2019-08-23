package com.jerseyexample.app.converters;


import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.domain.responses.UserDetailsResponse;
import com.jerseyexample.app.model.UserDescriptionEntity;
import com.jerseyexample.app.model.UserEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDetailsConverter {

    private static final ModelMapper mapper = new ModelMapper();

    public static UserDetailsResponse toResponse(UserEntity userEntity) {
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        UserDetailsResponse response =  mapper.map(userEntity, UserDetailsResponse.class);

        Optional<UserDescriptionEntity> description = Optional.ofNullable(userEntity.getUserDescription());
        if(!description.isPresent())
            return response;

        response.setDescription(description.get().getDescription());
        response.setSkills(description.get().getSkills());
        response.setExperience(description.get().getExperience());

        return response;
    }

    public static List<UserDetailsResponse> toResponse(List<UserEntity> userEntities) {
        return userEntities.stream().map(userEntity -> toResponse(userEntity)).collect(Collectors.toList());
    }

    public static UserEntity toEntity(UserRequest userRequest) {
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        return mapper.map(userRequest, UserEntity.class);
    }
}
