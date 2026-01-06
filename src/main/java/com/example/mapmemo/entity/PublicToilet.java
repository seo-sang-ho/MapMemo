package com.example.mapmemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PublicToilet {

    @Id
    private String publicId;

    private String name;
    private String roadAddress;

    private Double latitude;
    private Double longitude;

    private String openTime;
}
