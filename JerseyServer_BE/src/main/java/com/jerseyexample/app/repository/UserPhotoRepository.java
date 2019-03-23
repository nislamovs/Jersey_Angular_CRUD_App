package com.jerseyexample.app.repository;


import com.jerseyexample.app.model.UserPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhotoEntity, Long> {
}