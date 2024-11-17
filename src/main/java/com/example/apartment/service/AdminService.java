package com.example.apartment.service;

import com.example.apartment.model.User;
import com.example.apartment.respository.BoardRepository;
import com.example.apartment.respository.CommentRepository;
import com.example.apartment.type.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private CommentRepository commentRepository;

  // 어드민 권한 확인 메서드
  public boolean isAdmin(User user) {
    return user.getRole().equals(UserRole.ADMIN);  // 수정: == 대신 equals() 사용
  }

  // 어드민 게시글 삭제 메서드
  public void deleteBoardByAdmin(Long postId) {
    boardRepository.deleteById(postId);
  }

  // 어드민 댓글 삭제 메서드
  public void deleteCommentByAdmin(Long commentId) {
    commentRepository.deleteById(commentId);
  }
}