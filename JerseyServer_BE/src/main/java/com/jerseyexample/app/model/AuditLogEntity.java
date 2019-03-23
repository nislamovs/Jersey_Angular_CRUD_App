package com.jerseyexample.app.model;

import com.jerseyexample.app.domain.enums.DBOperation;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity(name="AuditLog")
@Table(name="auditlog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@EntityListeners(AuditingEntityListener.class)
public class AuditLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Enumerated(STRING)
    @Column(name="operation")
    private DBOperation operation;

    @NotEmpty
    @Column(name="username")
    private String username;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "event_date")
    private Date eventDate;
}

