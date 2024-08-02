package com.ormi.cookudasse.controller;

import com.ormi.cookudasse.dto.request.PostRequest;
import com.ormi.cookudasse.dto.response.PostSaveResponse;
import com.ormi.cookudasse.entitiy.FoodCategory;
import com.ormi.cookudasse.entitiy.Post;
import com.ormi.cookudasse.entitiy.PostDetail;
import com.ormi.cookudasse.entitiy.User;
import com.ormi.cookudasse.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/write")
    public String showWriteForm(Model model) {
        model.addAttribute("postDetail", new PostDetail());
        model.addAttribute("foodCategories", FoodCategory.values());
        return "writePost";
    }

    @PostMapping(value = "/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostSaveResponse> createPost(@ModelAttribute PostRequest postRequest,
                                                       @RequestPart(value = "file", required = false) MultipartFile file) {
        User user = new User(); // 임시 사용자 생성
        user.setId(1L);
        PostSaveResponse response = postService.createPost(postRequest, user, file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post.getPostDetail());
        return "postDetail";
    }

    @GetMapping("/post/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("foodCategories", FoodCategory.values());
        return "editPost";
    }

    @PostMapping("/post/{id}/edit")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostDetail updatedPostDetail) {
        postService.updatePost(id, updatedPostDetail);
        return "redirect:/post/" + id;
    }

    @PostMapping("/post/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/";


    }
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestPart("file") MultipartFile file) {
        // 파일 처리 로직
        return ResponseEntity.ok("File uploaded successfully");
    }
}
