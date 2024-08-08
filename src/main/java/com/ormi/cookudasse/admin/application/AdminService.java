package com.ormi.cookudasse.admin.application;

import com.ormi.cookudasse.admin.dto.AdminRequest;
import com.ormi.cookudasse.auth.domain.Role;
import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.auth.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(userRepository.findAllByRole(Role.BANNED));
        users.addAll(userRepository.findAllByRole(Role.ORDINARY));
        return users;
    }

    public void checkAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole().equals(Role.BANNED)) {
            throw new RuntimeException("정지된 회원은 접근이 제한된 기능입니다.");
        }
    }

    @Transactional
    public User updateAdmin(AdminRequest request) {
        User findUser = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        findUser.setRole(request.getRole());
        return userRepository.save(findUser);
    }
}
