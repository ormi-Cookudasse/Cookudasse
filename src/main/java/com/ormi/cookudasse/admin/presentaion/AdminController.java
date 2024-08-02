package com.ormi.cookudasse.admin.presentaion;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.ormi.cookudasse.admin.dto.AdminRequest;
import com.ormi.cookudasse.admin.application.AdminService;
import com.ormi.cookudasse.auth.domain.Role;
import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserRepository userRepository;

//    @GetMapping
//    public ResponseEntity<?> getAdmin(Long userId) {
//        User user = UserRepository.findById(userId);
//        if (Role.MANAGER.equals(user.getRole())) {
//            return ResponseEntity.ok(response);
//        } else return ResponseEntity.notFound().build();

//    }

    // <관리자 페이지> 버튼 눌렀을 때 나오는 페이지 admin.html : 우선 사용자 목록 보여주기 -> 이름, email, ROLE 형식 목록, 이후 ROLE 은 select 창으로 변경
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin"; // 이후 관리자 페이지 admin.html 만들어야 함.
    }

    // 권한 변경
    @PostMapping
    public String updateAdmin(@RequestBody AdminRequest request, Model model) {
        User updatedUser = adminService.updateAdmin(request);
        model.addAttribute("user", updatedUser);
        return "redirect:/";
    }

    // Role ENUM을 anmin.html로 전송
    @GetMapping
    public String listAuth(Model model) {
        model.addAttribute("role", Role.values());
        return "admin";
    }


}
