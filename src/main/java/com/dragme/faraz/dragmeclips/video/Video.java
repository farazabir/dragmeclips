package com.dragme.faraz.dragmeclips.video;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String originalPath;
    private String processedPath;
    private String thumbnailPath;

    private String userId;
    private String userEmail;
}
