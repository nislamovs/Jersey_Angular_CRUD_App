package com.jerseyexample.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data @Builder
@Entity(name = "UserDescriptions")
@Table(name = "user_descriptions")
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class UserDescriptionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "skills")
    private String skills;

    @Column(name = "experience")
    private String experience;
}