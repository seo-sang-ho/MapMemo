package com.example.mapmemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @Enumerated(EnumType.STRING) // db에는 문자열로 저장
    private Category category;

    private double latitude;
    private double longitude;

    public Memo(String title, String content, Category category, double latitude, double longitude) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void update(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
