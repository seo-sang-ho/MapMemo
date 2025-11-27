package com.example.mapmemo.controller;

import com.example.mapmemo.config.JwtUtil;
import com.example.mapmemo.entity.LoginRequest;
import com.example.mapmemo.entity.Member;
import com.example.mapmemo.entity.SignupRequest;
import com.example.mapmemo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {

        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .roles("USER")
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        return "회원가입 완료";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        Member dbMember = memberRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        if (!passwordEncoder.matches(request.getPassword(), dbMember.getPassword())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        return jwtUtil.generateToken(dbMember.getLoginId());
    }
}
