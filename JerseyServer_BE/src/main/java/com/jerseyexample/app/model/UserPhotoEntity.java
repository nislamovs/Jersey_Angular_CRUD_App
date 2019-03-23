package com.jerseyexample.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data @Builder
@Entity(name = "UserPhotos")
@Table(name = "user_photos")
@NoArgsConstructor
@AllArgsConstructor
//@Audited
public class UserPhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "photo")
    private byte[] photoImage;
}