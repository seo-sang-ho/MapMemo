package com.example.mapmemo.repository;

import com.example.mapmemo.entity.Category;
import com.example.mapmemo.entity.Memo;
import com.example.mapmemo.entity.MemoSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.mapmemo.entity.QMemo.memo;

@Repository
@RequiredArgsConstructor
public class MemoRepositoryImpl implements MemoRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemoRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Memo> searchMemos(MemoSearchCondition condition) {
        return queryFactory
                .selectFrom(memo)
                .where(
                        categoryEq(condition.getCategory()),
                        titleContains(condition.getTitle())
                )
                .fetch();
    }

    private BooleanExpression categoryEq(Category category) {
        return category != null ? memo.category.eq(category) : null;
    }

    private BooleanExpression titleContains(String title) {
        return title != null ? memo.title.contains(title) : null;
    }
}
