package com.jerseyexample.app.model;


import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedBy
    @Column(name = "createdby", nullable = false, updatable = false)
    protected String createdBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "modifiedby", nullable = false)
    protected String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

}
