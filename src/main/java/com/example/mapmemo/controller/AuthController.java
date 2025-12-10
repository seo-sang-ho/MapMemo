package com.example.mapmemo.controller;

import com.example.mapmemo.config.JwtUtil;
import com.example.mapmemo.entity.LoginRequest;
import com.example.mapmemo.entity.Member;
import com.example.mapmemo.entity.SignupRequest;
import com.example.mapmemo.repository.MemberRepository;
import com.example.mapmemo.security.CustomUserDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {

        Member member = memberRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        // Access, Refresh Token
        String accessToken = jwtUtil.generateAccessToken(member.getLoginId());
        String refreshToken = jwtUtil.generateRefreshToken();

        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);

        // refresh Token -> httpOnly 쿠키 저장
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7일
        response.addCookie(cookie);

        return ResponseEntity.ok(accessToken);

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .path("/")
                .maxAge(0)  // 즉시 삭제
                .build();

        response.addHeader("Set-Cookie", deleteCookie.toString());

        return ResponseEntity.ok("Logged out");
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue("refreshToken") String refreshToken) {

        // Refresh Token 유효성 검증
        if (!jwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(401).body("Refresh Token invalid");
        }

        // DB에 저장된 Refresh Token과 비교
        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("로그인 다시 필요"));

        // Access Token 재발급
        String newAccessToken = jwtUtil.generateAccessToken(member.getLoginId());

        return ResponseEntity.ok(newAccessToken);
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("id") String loginId) {
        Member loginMember = memberRepository.findByLoginId(loginId).orElseThrow();

        return "로그인 된 회원정보";
    }

    @GetMapping("/me")
    public CustomUserDetails me(@AuthenticationPrincipal CustomUserDetails user) {
        return user;
    }


}
