package com.example.mapmemo.service;

import com.example.mapmemo.entity.Member;
import com.example.mapmemo.entity.MemberJoinRequestDto;
import com.example.mapmemo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public Long join(MemberJoinRequestDto request){
        if (memberRepository.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다. 아이디 찾기를 해주세요.");
        }

        // 비밀번호 암호화
        String encodePw = passwordEncoder.encode(request.getPassword());

        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .email(request.getEmail())
                .name(request.getName())
                .roles("ROLE_USER")
                .build();

        memberRepository.save(member);

        return member.getId();
    }
}
