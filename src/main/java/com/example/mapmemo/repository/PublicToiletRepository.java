package com.example.mapmemo.repository;

import com.example.mapmemo.entity.PublicToilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicToiletRepository extends JpaRepository<PublicToilet, String> {
}
