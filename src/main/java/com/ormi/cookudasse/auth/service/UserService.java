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

    log.info("======== " + request.getAdminAnswer() + "===========");

    Role role = request.getAdminAnswer().equals("Spring!") ? Role.MANAGER : Role.ORDINARY;

    // request로 받아온 정보와 Role.ORDINARY로 회원 build
    User user =
        User.builder()
            .email(request.getEmail())
            .password(request.getPassword()) // 주의: 실제로는 비밀번호를 해시화해야 합니다
            .username(request.getUsername())
            .role(role) // 기본 역할을 ORDINARY, 가입 시 질문 정답일 경우 MANAGER
            .build();

    // repository에 저장
    return userRepository.save(user);
  }

  public User authenticate(String email, String password) {
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    //        if (user.getRole() == Role.BANNED) {
    //            throw new RuntimeException("This account is banned");
    //        }
    if (user != null && user.getPassword().equals(password)) {
      return user;
    }
    return null;
    //        return user.getPassword().equals(password); // 주의: 실제로는 안전한 비교 방법을 사용해야 합니다
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

  public void initiatePasswordReset(String email) {
    User user = findByEmail(email);
    // 비밀번호 재설정 로직 구현 (예: 임시 비밀번호 생성 및 이메일 발송)
    System.out.println("Password reset initiated for user: " + user.getUsername());
  }

  public void changeUserRole(String email, Role newRole) {
    User user = findByEmail(email);
    user.setRole(newRole);
    userRepository.save(user);
  }
}
