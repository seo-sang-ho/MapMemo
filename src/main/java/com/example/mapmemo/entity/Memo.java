package com.example.mapmemo.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
public class Memo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING) // db에는 문자열로 저장
    private Category category;

    private double latitude;
    private double longitude;

    public Memo(String title, String content, double latitude, double longitude) {
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
