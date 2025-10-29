package com.example.mapmemo.entity;

import lombok.Data;

@Data
public class MemoSearchCondition {
    private Category category;
    private String title;
    private Double minLat;
    private Double maxLat;
    private Double minLng;
    private Double maxLng;
}
