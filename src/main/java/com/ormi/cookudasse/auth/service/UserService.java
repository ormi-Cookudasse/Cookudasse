package com.ormi.cookudasse.auth.service;

import com.ormi.cookudasse.auth.domain.Role;
import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.auth.dto.SignupRequest;
import com.ormi.cookudasse.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public User registerUser(SignupRequest request) {
    // 이메일 중복 검사
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new RuntimeException("Email already exists");
    }

    // request로 받아온 정보와 Role.ORDINARY로 회원 build
    User user =
        User.builder()
            .email(request.getEmail())
            .password(request.getPassword()) // 주의: 실제로는 비밀번호를 해시화해야 합니다
            .username(request.getUsername())
            .role(Role.ORDINARY) // 기본 역할을 ORDINARY로 설정
            .build();

    // repository에 저장
    return userRepository.save(user);
  }

  public User authenticate(String email, String password) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    if (!user.getPassword().equals(password)) { // 실제로는 암호화된 비밀번호를 비교해야 함
      throw new RuntimeException("Invalid password");
    }
    return user;
  }

  public User findByEmail(String email) {
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public void deleteUser(String email) {
    User user = findByEmail(email);
    userRepository.delete(user);
  }

  public String findUsernameByEmail(String email) {
    return findByEmail(email).getUsername();
  }

  public String initiatePasswordReset(String email) {
    User user = findByEmail(email);
    // 실제 비밀번호 재설정 대신 메시지 반환
    String message = "비밀번호 재설정 링크가 " + user.getEmail() + "로 전송되었습니다. (실제로는 전송되지 않음)";
    System.out.println(message);
    return message;
  }

  public void changeUserRole(String email, Role newRole) {
    User user = findByEmail(email);
    user.setRole(newRole);
    userRepository.save(user);
  }
}
