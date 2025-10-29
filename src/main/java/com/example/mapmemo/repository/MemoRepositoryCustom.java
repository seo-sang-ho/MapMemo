package com.example.mapmemo.repository;

import com.example.mapmemo.entity.Memo;
import com.example.mapmemo.entity.MemoSearchCondition;

import java.util.List;

public interface MemoRepositoryCustom {
    List<Memo> searchMemos(MemoSearchCondition condition);
}
