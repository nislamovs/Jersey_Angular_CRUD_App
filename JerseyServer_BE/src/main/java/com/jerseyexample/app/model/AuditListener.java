package com.jerseyexample.app.model;

import com.jerseyexample.app.domain.enums.DBOperation;
import com.jerseyexample.app.model.AuditLogEntity;
import com.jerseyexample.app.model.UserEntity;
import com.jerseyexample.app.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.PreRemove;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

//@EntityListeners(AuditingEntityListener.class)
public class AuditListener {

//    @Autowired
//    AuditLogRepository auditLogRepository;
//
//    @PrePersist
//    private void afterPersist(UserEntity userEntity) {
//        beforeAnyOperation(userEntity, DBOperation.INSERTED);
//    }
//
//    @PreUpdate
//    private void afterUpdate(UserEntity userEntity) {
//        beforeAnyOperation(userEntity, DBOperation.UPDATED);
//    }
//
//    @PreRemove
//    private void afterRemove(UserEntity userEntity) {
//        beforeAnyOperation(userEntity, DBOperation.DELETED);
//    }
//
//    private void beforeAnyOperation(UserEntity userEntity, DBOperation operation) {
//        AuditLogEntity logEntity = AuditLogEntity.builder()
//                .username(userEntity.getFirstname() + " " + userEntity.getLastname())
//                .operation(operation)
//                .eventDate(userEntity.getLastModifiedDate())
//                .build();
//
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>...             "+logEntity.toString());
//        auditLogRepository.save(logEntity);
//    }

}