package com.example.mapmemo.controller;

import com.example.mapmemo.entity.Memo;
import com.example.mapmemo.service.MemoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/memos")
public class MemoController {

    private final MemoService memoService;

    @GetMapping
    public List<Memo> getAllMemos(){
        return memoService.getAllMemos();
    }

    @PostMapping
    public Memo createMemo(@RequestBody Memo memo) {
        return memoService.save(memo);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
    }
}
