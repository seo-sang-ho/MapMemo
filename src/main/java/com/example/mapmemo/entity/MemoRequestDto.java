package com.example.mapmemo.entity;

import lombok.*;

@Getter
@NoArgsConstructor
public class MemoRequestDto {

    private String title;
    private String content;
    private Category category;
    private double latitude;
    private double longitude;

    @Builder
    public MemoRequestDto(String title, String content, Category category, double latitude, double longitude) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
