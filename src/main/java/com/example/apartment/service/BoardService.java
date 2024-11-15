package com.example.apartment.service;

import com.example.apartment.config.auth.PrincipalDetail;
import com.example.apartment.dto.CommentRequestDto;
import com.example.apartment.model.Board;
import com.example.apartment.model.Comment;
import com.example.apartment.model.User;
import com.example.apartment.respository.BoardRepository;
import com.example.apartment.respository.CommentRepository;
import com.example.apartment.respository.UserRepository;
import com.example.apartment.type.BoardStatus;
import com.example.apartment.type.CommentStatus;
import com.example.apartment.type.UserStatus;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BoardRepository boardRepository;

  public BoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  @Transactional
  public void writePost(Board board, User user) {
    board.setUser(user);
    board.setStatus(BoardStatus.ACTIVE);  // 게시글 상태를 ACTIVE로 설정
    boardRepository.save(board);
  }

  @Transactional(readOnly = true)
  public Board getPost(long id) {
    return boardRepository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("게시글을 찾을 수 없습니다.")); // 한국어 에러 메시지
  }


  @Transactional
  public void updatePost(long id, Board requestBoard) {
    Board board = boardRepository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("게시글을 찾을 수 없습니다.")); // 한국어 에러 메시지
    board.setTitle(requestBoard.getTitle());
    board.setContent(requestBoard.getContent()); // 자동업데이트
    boardRepository.save(board); // 상태를 변경한 후 저장
  }

  @Transactional
  public void deletePost(long postId) {
    Board board = boardRepository.findById(postId).orElseThrow(() ->
        new IllegalArgumentException("게시글을 찾을 수 없습니다."));

    // 활성화된 사용자의 경우 물리적 삭제, 탈퇴된 사용자의 경우 논리적 삭제
    String currentUserEmail = getCurrentUserEmail();  // 현재 로그인한 사용자 이메일 조회

    if (board.getUser().getEmail().equals(currentUserEmail)) {
      if (board.getUser().getStatus() == UserStatus.ACTIVE) {
        // 물리적 삭제: 활성화된 사용자의 게시글을 삭제
        boardRepository.delete(board);
      } else {
        // 논리적 삭제: 탈퇴한 사용자의 게시글을 DELETED 상태로 변경
        board.setStatus(BoardStatus.DELETED);
        boardRepository.save(board);
      }
    } else {
      throw new IllegalArgumentException("본인 게시글만 삭제할 수 있습니다.");
    }
  }

  @Transactional
  public void writeComment(CommentRequestDto commentDto) {
    Board board = boardRepository.findById(commentDto.getBoardId()).orElseThrow(() ->
        new IllegalArgumentException("게시글을 찾을 수 없습니다.")); // 한국어 에러 메시지

    User user = userRepository.findById(commentDto.getUserId()).orElseThrow(() ->
        new IllegalArgumentException("사용자를 찾을 수 없습니다.")); // 한국어 에러 메시지

    Comment comment = Comment.builder()
        .user(user)
        .board(board)
        .content(commentDto.getContent())
        .status(CommentStatus.ACTIVE)  // 댓글 상태를 ACTIVE로 설정
        .build();

    commentRepository.save(comment);
  }


  @Transactional(readOnly = true)
  public Page<Board> searchPosts(String keyword, Pageable pageable) {
    return boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
  }

  // 전체 게시글 목록 페이징 처리
  public Page<Board> getPostList(Pageable pageable) {
    return boardRepository.findAll(pageable);  // findAll 대신, 원하는 조건으로 검색 메서드를 호출할 수 있습니다.
  }

  @Transactional
  public void deleteComment(long commentId) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
        new IllegalArgumentException("댓글을 찾을 수 없습니다."));

    // 활성화된 사용자의 경우 물리적 삭제, 탈퇴된 사용자의 경우 논리적 삭제
    String currentUserEmail = getCurrentUserEmail();  // 현재 로그인한 사용자 이메일 조회
    System.out.println("현재 로그인한 이메일: " + currentUserEmail);  // 디버깅용 로그

    if (comment.getUser().getEmail().equals(currentUserEmail)) {
      if (comment.getUser().getStatus() == UserStatus.ACTIVE) {
        // 물리적 삭제: 활성화된 사용자의 댓글을 삭제
        commentRepository.delete(comment);
        System.out.println("댓글 삭제됨");  // 디버깅용 로그

      } else {
        // 논리적 삭제: 탈퇴한 사용자의 댓글을 DELETED 상태로 변경
        comment.setStatus(CommentStatus.DELETED);
        commentRepository.save(comment);
        System.out.println("댓글 논리적 삭제됨");  // 디버깅용 로그
      }
    } else {
      throw new IllegalArgumentException("본인 댓글만 삭제할 수 있습니다.");
    }
  }

  private String getCurrentUserEmail() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();

      // 일반 로그인일 경우
      if (principal instanceof PrincipalDetail) {
        return ((PrincipalDetail) principal).getEmail(); // 이메일 반환
      }
      // OAuth2 로그인일 경우
      else if (principal instanceof OAuth2User) {
        Map<String, Object> attributes = ((OAuth2User) principal).getAttributes();
        return (String) attributes.get("email"); // OAuth2에서 이메일 반환
      }
    }
    return null; // 인증되지 않은 경우
  }

}