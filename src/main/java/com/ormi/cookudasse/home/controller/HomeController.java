package com.ormi.cookudasse.home.controller;

import com.ormi.cookudasse.notice.application.NoticeService;
import com.ormi.cookudasse.notice.domain.Notice;
import com.ormi.cookudasse.post.entitiy.Post;
import com.ormi.cookudasse.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PostService postService;
    private final NoticeService noticeService;

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.getAllPosts();
        List<Notice> notices = noticeService.get5Notices();
        model.addAttribute("posts", posts);
        model.addAttribute("notices", notices);
        return "home";
    }
}
