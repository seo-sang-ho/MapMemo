package com.example.mapmemo.service;

import com.example.mapmemo.entity.*;
import com.example.mapmemo.repository.MemberRepository;
import com.example.mapmemo.repository.MemoRepository;
import com.example.mapmemo.security.CustomUserDetails;
import com.example.mapmemo.security.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final MemberRepository memberRepository;

    public List<Memo> getMyMemos(Long memberId){
        return memoRepository.findByMemberId(memberId);
    }

    public Memo save(MemoRequestDto memoRequestDto, CustomUserDetails userDetails) {

        Member loginMember = memberRepository.findByLoginId(userDetails.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        Memo memo = Memo.builder()
                .title(memoRequestDto.getTitle())
                .content(memoRequestDto.getContent())
                .category(memoRequestDto.getCategory())
                .latitude(memoRequestDto.getLatitude())
                .longitude(memoRequestDto.getLongitude())
                .member(loginMember)
                .build();

        return memoRepository.save(memo);
    }

    public void delete(Long id) {
        memoRepository.deleteById(id);
    }

    public List<Memo> findByCategory(Category category) {
        return memoRepository.findByCategory(category);
    }

    public List<Memo> searchMemos(MemoSearchCondition condition) {
        return memoRepository.searchMemos(condition);
    }
}
