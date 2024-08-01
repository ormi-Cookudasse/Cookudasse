package com.ormi.cookudasse.controller;

import com.ormi.cookudasse.dto.FindPasswordRequestDto;
import com.ormi.cookudasse.dto.LoginRequestDto;
import com.ormi.cookudasse.dto.SignupRequestDto;
import com.ormi.cookudasse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginRequestDto> login(@RequestBody LoginRequestDto loginRequest) {
        // 로그인 로직 구현
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupRequestDto> signup(@RequestBody SignupRequestDto signupRequest) {
        // 회원가입 로직 구현
    }

    @GetMapping("/find-id")
    public ResponseEntity<String> findId(@RequestParam String email) {
        // 아이디 찾기 로직 구현
    }

    @PostMapping("/find-password")
    public ResponseEntity<FindPasswordRequestDto> findPassword(@RequestBody FindPasswordRequestDto request) {
        // 비밀번호 찾기 로직 구현
    }
}