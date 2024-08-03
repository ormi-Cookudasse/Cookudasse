package com.ormi.cookudasse.home.controller;

import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.notice.application.NoticeService;
import com.ormi.cookudasse.notice.domain.Notice;
import com.ormi.cookudasse.post.entitiy.Post;
import com.ormi.cookudasse.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
  private final PostService postService;
  private final NoticeService noticeService;

  //    @GetMapping("/")
  //    public String home(Model model) {
  //        List<Post> posts = postService.getAllPosts();
  //        List<Notice> notices = noticeService.get5Notices();
  //        model.addAttribute("posts", posts);
  //        model.addAttribute("notices", notices);
  //        return "home";
  //    }
  @GetMapping("/")
  public String home(Model model, HttpSession session, @ModelAttribute("errorMessage") String errorMessage) {
    List<Post> posts = postService.getAllPosts();
    List<Notice> notices = noticeService.get5Notices();
    model.addAttribute("posts", posts);
    model.addAttribute("notices", notices);

    // 세션에서 사용자 정보를 가져와 모델에 추가
    User user = (User) session.getAttribute("user");
    if (user != null) {
      model.addAttribute("user", user);
    }

    if (errorMessage != null && !errorMessage.isEmpty()) {
      model.addAttribute("errorMessage", errorMessage);
    }

    return "home";
  }
}
