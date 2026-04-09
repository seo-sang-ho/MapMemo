package com.example.mapmemo.repository;

import com.example.mapmemo.entity.PublicToilet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicToiletRepository extends JpaRepository<PublicToilet, String>, PublicToiletQueryRepository {
    @Query("""
    select pt from PublicToilet pt
    where pt.latitude between :minLat and :maxLat
      and pt.longitude between :minLng and :maxLng
    """)
    List<PublicToilet> findInBounds(
            @Param("minLat") double minLat,
            @Param("maxLat") double maxLat,
            @Param("minLng") double minLng,
            @Param("maxLng") double maxLng
    );

}
