package com.jerseyexample.app.model;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.envers.DefaultRevisionEntity;
//import org.hibernate.envers.RevisionEntity;
//
//import javax.persistence.*;
//
//@Entity
//@RevisionEntity()
//@Table(name = "REVINFO", schema = "audit")
//@AttributeOverrides({
//        @AttributeOverride(name = "timestamp", column = @Column(name = "REVTSTMP")),
//        @AttributeOverride(name = "id", column = @Column(name = "REV"))})
//@Getter
//@Setter
//public class AuditRevisionEntity extends DefaultRevisionEntity {
//
//    @Column(name = "USERNAME", nullable = false)
//    private String username;
//}
public class AuditRevisionEntity {}