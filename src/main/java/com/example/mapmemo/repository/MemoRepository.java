package com.example.mapmemo.repository;

import com.example.mapmemo.entity.Category;
import com.example.mapmemo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByCategory(Category category);
}
