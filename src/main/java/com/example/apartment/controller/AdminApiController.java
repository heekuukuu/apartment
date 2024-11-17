package com.example.apartment.controller;

import com.example.apartment.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

  @Autowired
  private BoardService boardService;


  // 어드민 게시글 삭제 (API)
  @DeleteMapping("/boards/{postId}")
  public ResponseEntity<String> deleteBoardByAdmin(@PathVariable Long postId) {
    try {
      boardService.deletePostByAdmin(postId);
      return ResponseEntity.ok("게시글 삭제 완료!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 삭제 실패!");
    }
  }

  // 어드민 댓글 삭제 (API)
  @DeleteMapping("/comments/{commentId}")
  public ResponseEntity<String> deleteCommentByAdmin(@PathVariable Long commentId) {
    try {
      boardService.deleteCommentByAdmin(commentId);
      return ResponseEntity.ok("댓글 삭제 완료!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제 실패!");
    }
  }
}