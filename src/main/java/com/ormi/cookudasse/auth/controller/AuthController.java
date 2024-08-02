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
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest signupRequest, Model model) {
        try {
            User user = userService.registerUser(signupRequest);
            model.addAttribute("message", "User registered successfully");
            return "signupSuccess";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signupError";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("message", "Logged out successfully");
        return "logoutSuccess";
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
}