package com.ormi.cookudasse.admin.application;

import com.ormi.cookudasse.admin.dto.AdminRequest;
import com.ormi.cookudasse.auth.domain.Role;
import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.auth.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    //유저 권한 확인
    public void userRoleCheck(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null && user.getRole().equals(Role.BANNED)) {
            throw new RuntimeException("회원권한이 정지되어 공지사항만 열람가능합니다.");
        }
    }

    @Transactional
    public User updateAdmin(AdminRequest request) {
        // 1. 관리자 여부 검증
        // 로그인 방식에 따라 request dto 안에 관리자를 검증할 필드(id 등)를 추가하거나, 아님 따로 받아와서 관리자 여부 검증
        // -> 그런데 이건 api 라서, 관리자 페이지 접근 시 처음부터 검증해도 무관!


        // 2. request dto 의 해당 회원 id 를 가져오고, UserRepository.findById(userId) 로 접근해서, 해당 회원의 권한(auth?) 필드 조정(일반과 정지 둘 중 하나)
        User findUser = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        findUser.setRole(request.getRole());
        return userRepository.save(findUser);

    }
}
