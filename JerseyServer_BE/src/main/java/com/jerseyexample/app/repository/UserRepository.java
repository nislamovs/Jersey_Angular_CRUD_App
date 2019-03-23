package com.jerseyexample.app.repository;

import com.jerseyexample.app.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findById(final Long id);

    Optional<UserEntity> findByFirstnameContainingIgnoreCase(final String firstname);
}