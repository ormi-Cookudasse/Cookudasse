package com.ormi.cookudasse.admin.presentaion;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.ormi.cookudasse.admin.dto.AdminRequest;
import com.ormi.cookudasse.admin.application.AdminService;
import com.ormi.cookudasse.auth.domain.Role;
import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final UserRepository userRepository;

    // <관리자 페이지> 버튼 눌렀을 때 나오는 페이지 admin.html : 우선 사용자 목록 보여주기 -> 이름, email, ROLE 형식 목록, 이후 ROLE 은
    // select 창으로 변경
    @GetMapping("/users")
    public String getAllUsers(Model model, HttpSession session) {
        checkAdmin(session);
        List<User> users = new ArrayList<>();
        users.addAll(userRepository.findAllByRole(Role.BANNED));
        users.addAll(userRepository.findAllByRole(Role.ORDINARY));
        model.addAttribute("users", users);
        model.addAttribute("role", Role.values());
        return "admin"; // 이후 관리자 페이지 admin.html 만들어야 함.
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateAdmin(@RequestBody List<AdminRequest> requests) {
        List<User> updatedUsers = new ArrayList<>();
        for (AdminRequest request : requests) {
            User updatedUser = adminService.updateAdmin(request);
            updatedUsers.add(updatedUser);
        }
        return ResponseEntity.ok(updatedUsers);
    }


    // Role ENUM을 anmin.html로 전송
    @GetMapping
    public String listAuth(Model model) {
        model.addAttribute("role", Role.values());
        return "admin";
    }

    public void checkAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals(Role.MANAGER)) {
            throw new RuntimeException("관리자만 접근할 수 있습니다.");
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:" + request.getHeader("Referer");
    }
}
