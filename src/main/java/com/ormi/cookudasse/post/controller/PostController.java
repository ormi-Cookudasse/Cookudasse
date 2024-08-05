package com.ormi.cookudasse.post.controller;

import com.ormi.cookudasse.auth.domain.Role;
import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.post.dto.request.PostRequest;
import com.ormi.cookudasse.post.dto.response.PostSaveResponse;
import com.ormi.cookudasse.post.entitiy.FoodCategory;
import com.ormi.cookudasse.post.entitiy.Post;
import com.ormi.cookudasse.post.entitiy.PostDetail;
import com.ormi.cookudasse.post.service.CommentService;
import com.ormi.cookudasse.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/write")
    public String showWriteForm(Model model, HttpSession session) {
    checkAdmin(session);
        model.addAttribute("postDetail", new PostDetail());
        model.addAttribute("foodCategories", FoodCategory.values());
        return "writePost";
    }
    @PostMapping(value = "/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createPost(@ModelAttribute PostRequest postRequest,
                             @RequestPart(value = "file", required = false) MultipartFile file) {

        User user = new User(); // 임시 사용자 생성
        user.setId(1L);
        postService.createPost(postRequest, user, file);
        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public String showPost(@PathVariable(name = "id") Long id, Model model, HttpSession session) {
        checkAdmin(session);
        Post post = postService.getPostById(id);
        postService.incrementView(id);  // 조회수 증가
        model.addAttribute("post", post.getPostDetail());
        model.addAttribute("comments", commentService.getCommentsByPost(post));
        return "postDetail";
    }
    @PostMapping("/post/{id}/comment")
    public String addComment(@PathVariable Long id, @RequestParam String content) {
        Post post = postService.getPostById(id);
        commentService.saveComment(post, content, "Anonymous"); // 임시로 작성자를 "Anonymous"로 설정
        return "redirect:/post/" + id;
    }

    @GetMapping("/post/{id}/edit")
    public String showEditForm(@PathVariable(name = "id") Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("foodCategories", FoodCategory.values());
        return "editPost";
    }

    // 우선 pathvariabe 적용
    // requestDto 로 적용
    @PostMapping("/post/{id}/edit")
    public String updatePost(@PathVariable(name = "id") Long id, @ModelAttribute PostRequest postRequest) {
        postService.updatePost(id, postRequest);
        return "redirect:/post/" + id;
    }
    @PostMapping("/post/{id}/like")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> likePost(@PathVariable Long id) {
        int newLikeCount = postService.incrementLike(id);
        Map<String, Integer> response = new HashMap<>();
        response.put("likeCount", newLikeCount);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/post/{id}/delete")
    public String deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePost(id);
        return "redirect:/";


    }
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestPart("file") MultipartFile file) {
        // 파일 처리 로직
        return ResponseEntity.ok("File uploaded successfully");
    }


    private void checkAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole().equals(Role.BANNED)) {
            throw new RuntimeException("정지된 회원은 조회할 수 없는 콘텐츠입니다.");
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:" + request.getHeader("Referer");
    }
}
