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
      throw new RuntimeException("이미 사용중인 이메일입니다.");
    }

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
    User user = findByEmail(email);
    if (!user.getPassword().equals(password)) { // 실제로는 암호화된 비밀번호를 비교해야 함
      throw new RuntimeException("비밀번호가 틀렸습니다.");
    }
    return user;
  }

  public void deleteUser(String email) {
    User user = findByEmail(email);
    userRepository.delete(user);
  }

  public User findByEmail(String email) {
    log.info("======== " + email + "==========");
    return userRepository
            .findByEmail(email)
            .orElseThrow(() -> new RuntimeException("사용자 정보가 존재하지 않습니다."));
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
