package com.example.mapmemo.entity;

import lombok.Data;

@Data
public class MemoSearchCondition {
    private Category category;
    private String keyword; // title + content 통합검색
    private Long memberId;
}
