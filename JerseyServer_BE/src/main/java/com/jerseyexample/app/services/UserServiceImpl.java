package com.jerseyexample.app.services;


import com.jerseyexample.app.converters.UserConverter;
import com.jerseyexample.app.domain.exceptions.ImageConversionException;
import com.jerseyexample.app.domain.exceptions.UserNotFoundException;
import com.jerseyexample.app.domain.requests.UserRequest;
import com.jerseyexample.app.model.UserDescriptionEntity;
import com.jerseyexample.app.model.UserEntity;
import com.jerseyexample.app.model.UserPhotoEntity;
import com.jerseyexample.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findById(Long id) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findById(id);
        user.orElseThrow(() -> new UserNotFoundException("Username with id [" + id + "] not found."));

        return user.get();
    }

    @Override
    public UserEntity findByName(String name) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findByFirstnameContainingIgnoreCase(name);
        user.orElseThrow(() -> new UserNotFoundException("Username with name [" + name + "] not found."));

        return user.get();
    }

    @Override
    public void saveUser(UserRequest userRequest) {
        UserEntity user = UserConverter.toEntity(userRequest);
        byte[] image;
        if (Base64.isBase64(userRequest.getPhotoImage())) {
             image = Base64.decodeBase64(userRequest.getPhotoImage());
        } else {
            image = userRequest.getPhotoImage();
        }

        user.setUserPhoto(UserPhotoEntity.builder().photoImage(image)
                                                    .photoIcon(scale(image, 32, 32))
                                                    .id(userRequest.getId())
                                                    .build());

        user.setUserDescription(UserDescriptionEntity.builder().description(userRequest.getDescription())
                .skills(userRequest.getSkills()).experience(userRequest.getExperience())
                .id(userRequest.getId())
                .build());

        userRepository.save(user);
    }

    @Override
    public void updateUser(UserRequest user) throws UserNotFoundException {
        if (isUserExist(user)) {
            saveUser(user);
        } else {
            throw new UserNotFoundException("Username with id [" + user.getId() + "] not found.");
        }
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        if (isUserExist(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Username with id [" + id + "] not found.");
        }
    }

    @Override
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<UserEntity> findAllUsers(Integer pageSize, Integer pageNumber, String orderDirection, String orderBy) {
        if (pageNumber == null) pageNumber = 1;
        if (pageSize == null) pageSize = 5;
        if (orderDirection == null) orderDirection = "asc";
        if (orderBy == null) orderBy = "id";

        Pageable selectedRecords = PageRequest.of(pageNumber - 1, pageSize, Sort.Direction.fromString(orderDirection), orderBy);
        return userRepository.findAll(selectedRecords);
    }

    @Override
    public boolean isUserExist(UserRequest user) {
        return userRepository.existsById(user.getId());
    }

    @Override
    public boolean isUserExist(long id) {
        return userRepository.existsById(id);
    }

    @Override
    public long getUsersCount() {
        return userRepository.count();
    }

    private byte[] scale(byte[] fileData, int width, int height) throws ImageConversionException {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);

        try {
            BufferedImage img = ImageIO.read(in);
            System.out.println(img);
            if (height == 0) {
                height = (width * img.getHeight()) / img.getWidth();
            }
            if (width == 0) {
                width = (height * img.getWidth()) / img.getHeight();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "png", buffer);

            return buffer.toByteArray();
        } catch (IOException ex) {
            throw new ImageConversionException("Error converting image : ", ex);
        }
    }
}
