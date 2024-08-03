package com.ormi.cookudasse.auth.controller;

import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.auth.dto.FindPasswordRequest;
import com.ormi.cookudasse.auth.dto.LoginRequest;
import com.ormi.cookudasse.auth.dto.SignupRequest;
import com.ormi.cookudasse.auth.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpSession session, RedirectAttributes redirectAttributes) {
        User findUser = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (findUser != null) {
            session.setAttribute("user", findUser);
            return "redirect:/home";    // 다시 home.html 로
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid email or password");
            return "redirect:/login";   // 이후 login.html 에서
        }

//        try {
//            if (userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword())) {
//                session.setAttribute("email", loginRequest.getEmail());
//                model.addAttribute("message", "Login successful");
//                return "loginSuccess";
//            } else {
//                model.addAttribute("error", "Invalid credentials");
//                return "loginError";
//            }
//        } catch (RuntimeException e) {
//            model.addAttribute("error", e.getMessage());
//            return "loginError";
//        }

        // 교안에서 나온 것
        /*try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("로그인 성공");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 잘못된 인증 정보");
        }*/
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("signupRequest", new SignupRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest signupRequest, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.registerUser(signupRequest);
            redirectAttributes.addFlashAttribute("message", "User registered successfully");
            return "redirect:/api/auth/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/api/auth/signup";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("message", "Logged out successfully");
        return "redirect:/home";
    }

    @GetMapping("/find-id")
    public String findId(@RequestParam String email, Model model) {
        try {
            String username = userService.findUsernameByEmail(email);
            model.addAttribute("username", username);
            return "foundUsername";
        } catch (RuntimeException e) {
            model.addAttribute("error", "User not found");
            return "findIdError";
        }
    }

    @PostMapping("/find-password")
    public String findPassword(@RequestBody FindPasswordRequest request, Model model) {
        try {
            userService.initiatePasswordReset(request.getEmail());
            model.addAttribute("message", "Password reset initiated. Check your email.");
            return "passwordResetInitiated";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "passwordResetError";
        }
    }

    @PostMapping("/delete")
    public String deleteUser(HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                userService.deleteUser(user.getEmail());
                session.invalidate(); // 세션 무효화
                redirectAttributes.addFlashAttribute("message", "Your account has been successfully deleted.");
                return "redirect:/api/auth/login";
            } catch (RuntimeException e) {
                redirectAttributes.addFlashAttribute("error", "Failed to delete account: " + e.getMessage());
                return "redirect:/home";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to delete your account.");
            return "redirect:/api/auth/login";
        }
    }
}