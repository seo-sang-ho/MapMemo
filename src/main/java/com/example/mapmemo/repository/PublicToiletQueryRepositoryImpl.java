package com.example.mapmemo.repository;

import com.example.mapmemo.entity.PublicToilet;
import com.example.mapmemo.entity.QPublicToilet;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PublicToiletQueryRepositoryImpl
        implements PublicToiletQueryRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<PublicToilet> findNear(
            double lat,
            double lng,
            double radiusKm
    ) {
        QPublicToilet toilet = QPublicToilet.publicToilet;

        NumberExpression<Double> distance = Expressions.numberTemplate(
                Double.class,
                "6371 * acos(cos(radians({0})) * cos(radians({1})) * " +
                        "cos(radians({2}) - radians({3})) + sin(radians({0})) * sin(radians({1})))",
                lat,
                toilet.latitude,
                toilet.longitude,
                lng
        );

        return queryFactory
                .selectFrom(toilet)
                .where(
                        toilet.latitude.isNotNull(),
                        toilet.longitude.isNotNull(),
                        distance.loe(radiusKm)
                )
                .orderBy(distance.asc())
                .fetch();
    }
}
