package com.jerseyexample.app.repository;


import com.jerseyexample.app.model.UserDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDescriptionRepository extends JpaRepository<UserDescriptionEntity, Long> {
}