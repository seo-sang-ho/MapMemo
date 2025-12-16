package com.example.mapmemo.controller;

import com.example.mapmemo.entity.Memo;
import com.example.mapmemo.entity.MemoRequestDto;
import com.example.mapmemo.entity.MemoSearchCondition;
import com.example.mapmemo.entity.MemoUpdateDto;
import com.example.mapmemo.security.CustomUserDetails;
import com.example.mapmemo.service.MemoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/memos")
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/my")
    public ResponseEntity<List<Memo>> getAllMemos(@AuthenticationPrincipal CustomUserDetails user){
        Long loginId = user.getId();
        List<Memo> myMemos = memoService.getMyMemos(loginId);

        return ResponseEntity.ok(myMemos);
    }

    @PostMapping
    public Memo createMemo(@RequestBody MemoRequestDto memo,
                           @AuthenticationPrincipal CustomUserDetails user) {
        return memoService.save(memo, user);
    }
    @PutMapping("/{id}")
    public void updateMemo(@PathVariable Long id, @RequestBody MemoUpdateDto dto) {
        memoService.updateMemo(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoService.delete(id);
    }

    @GetMapping("/search")
    public List<Memo> searchMemo(@ModelAttribute MemoSearchCondition condition) {
        return memoService.searchMemos(condition);
    }

    @GetMapping("/profile")
    public String profile(Authentication auth) {
        return "현재 로그인한 사용자 : " + auth.getName();
    }
}
