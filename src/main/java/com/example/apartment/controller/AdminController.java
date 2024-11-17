package com.example.apartment.controller;

import com.example.apartment.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private BoardService boardService;




  // 어드민 게시글 삭제 (웹)
  @DeleteMapping("/boards/{postId}")
  public String deleteBoardByAdmin(@PathVariable Long postId) {
    try {
      boardService.deletePostByAdmin(postId);
      return "redirect:/admin/boards"; // 게시글 목록으로 리다이렉트
    } catch (Exception e) {
      return "error"; // 에러 페이지로 리다이렉트
    }
  }

  // 어드민 댓글 삭제 (웹)
  @DeleteMapping("/comments/{commentId}")
  public String deleteCommentByAdmin(@PathVariable Long commentId) {
    try {
      boardService.deleteCommentByAdmin(commentId);
      return "redirect:/admin/comments"; // 댓글 목록으로 리다이렉트
    } catch (Exception e) {
      return "error"; // 에러 페이지로 리다이렉트
    }
  }
}