package com.example.mapmemo.repository;

import com.example.mapmemo.entity.PublicToilet;

import java.util.List;

public interface PublicToiletQueryRepository {
    List<PublicToilet> findNear(
            double latitude,
            double longitude,
            double radius
    );
}
