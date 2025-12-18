package com.example.mapmemo.repository;

import com.example.mapmemo.entity.Category;
import com.example.mapmemo.entity.Memo;
import com.example.mapmemo.entity.MemoSearchCondition;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.mapmemo.entity.QMemo.memo;

@Repository
@RequiredArgsConstructor
public class MemoRepositoryImpl implements MemoRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Autowired
    public MemoRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Memo> searchMemos(MemoSearchCondition condition) {
        return queryFactory
                .selectFrom(memo)
                .where(
                        categoryEq(condition.getCategory()),
                        keywordContains(condition.getKeyword()),
                        memberEq(condition.getMemberId())
                )
                .fetch();
    }

    private BooleanExpression memberEq(Long memberId) {
        return memberId != null? memo.member.id.eq(memberId) : null;
    }

    private BooleanExpression categoryEq(Category category) {
        return category != null ? memo.category.eq(category) : null;
    }

    private BooleanExpression keywordContains(String keyword) {
        if (keyword == null || keyword.isBlank()) return null;

        return memo.title.contains(keyword)
                .or(memo.content.contains(keyword));
    }
}
