package com.example.mapmemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    private String content;

    private double latitude;
    private double longitude;

    public Memo(String title, String content, double latitude, double longitude) {
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
