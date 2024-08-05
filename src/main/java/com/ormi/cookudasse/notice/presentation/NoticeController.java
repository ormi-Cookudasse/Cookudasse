package com.ormi.cookudasse.notice.presentation;

import com.ormi.cookudasse.auth.domain.Role;
import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.notice.application.NoticeService;
import com.ormi.cookudasse.notice.domain.Notice;
import com.ormi.cookudasse.notice.dto.request.NoticeRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/notice")
@RequiredArgsConstructor
public class NoticeController {
  private final NoticeService noticeService;

  @GetMapping("/write")
  public String showWriteForm(Model model, HttpSession session) {
    checkAdmin(session);
    model.addAttribute("notice", new Notice());
    return "writeNotice";
  }

  @PostMapping(value = "/write")
  public String createNotice(@ModelAttribute NoticeRequest noticeRequest) {
    noticeService.createNotice(noticeRequest);
    return "redirect:/";
  }

  @GetMapping("/{id}")
  public String showNotice(@PathVariable(name = "id") Long id, Model model) {
    Notice noticeById = noticeService.getNoticeById(id);
    model.addAttribute("notice", noticeById);
    return "noticeDetail";
  }

  @GetMapping("/{id}/edit")
  public String showEditNotice(
      @PathVariable(name = "id") Long id, Model model, HttpSession session) {
    checkAdmin(session);
    Notice noticeById = noticeService.getNoticeById(id);
    model.addAttribute("notice", noticeById);
    return "editNotice";
  }

  @PostMapping("/{id}/edit")
  public String updateNotice(
      @PathVariable(name = "id") Long id, @ModelAttribute NoticeRequest noticeRequest) {
    noticeService.updateNotice(id, noticeRequest);
    return "redirect:/api/notice/" + id;
  }

  @PostMapping("/{id}/delete")
  public String deleteNotice(@PathVariable(name = "id") Long id) {
    noticeService.deleteNotice(id);
    return "redirect:/";
  }

  private void checkAdmin(HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null || !user.getRole().equals(Role.MANAGER)) {
      throw new RuntimeException("관리자가 아닙니다. 공지사항 작성은 관리자만 가능합니다.");
    }
  }
}
