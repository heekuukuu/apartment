package com.example.apartment.service;

import com.example.apartment.dto.CommentRequestDto;
import com.example.apartment.model.Board;
import com.example.apartment.model.Comment;
import com.example.apartment.model.User;
import com.example.apartment.respository.BoardRepository;
import com.example.apartment.respository.CommentRepository;
import com.example.apartment.respository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertThrows;


public class BoardServiceTest {
    @Mock
    private BoardRepository boardRepository;

    @Mock
    private CommentRepository commentRepository; // Mock 객체 생성

    @Mock
    private UserRepository userRepository; // Mock 객체 생성

    @InjectMocks
    private BoardService boardService; // 테스트 대상 클래스에 Mock 주입

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mock 객체 초기화
    }
    @Test
    void writePost_SuccessfulSave() { // 게시물 작성
        // given
        Board board = new Board();
        board.setTitle("Test Title!");
        board.setContent("Test Content!");

        User user = new User();
        user.setId(1L);
        user.setUsername(("testUser"));

        //when
        boardService.writePost(board, user);
        //then
        verify(boardRepository, times(1)).save(board);
    }
    @Test
    void  writeComment_SuccessfulSave() {
        //given
        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setBoardId(1L);
        commentRequestDto.setUserId(1L);
        commentRequestDto.setContent("Test Comment");


        Board board = new Board();
        board.setId(1L);

        User user = new User();
        user.setId(1L);

        when(boardRepository.findById(commentRequestDto.getBoardId())).thenReturn(Optional.of(board));
        when(userRepository.findById(commentRequestDto.getUserId())).thenReturn(Optional.of(user));

        // when: 테스트 실행 - 댓글 작성 메서드를 호출합니다.
        boardService.writeComment(commentRequestDto);

        // then: 결과 검증 - 댓글이 올바르게 저장되었는지 확인합니다.
        verify(commentRepository, times(1)).save(any(Comment.class));
    }


    @Test    //
    void writeComment_FailWhenBoardNotFound() {
        // given: 테스트 준비 - 유효하지 않은 게시물 ID로 DTO를 설정합니다.
        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setBoardId(2L);
        commentRequestDto.setUserId(1L);

        when(boardRepository.findById(commentRequestDto.getBoardId())).thenReturn(Optional.empty());

        // when, then: 테스트 실행 및 결과 검증 - 예외가 발생하는지 확인합니다.
        assertThrows(IllegalArgumentException.class, () -> boardService.writeComment(commentRequestDto));
    }



    @Test
    void getPostList() {
    }

    @Test
    void getPost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void writeComment() {
    }

    @Test
    void deleteComment() {
    }
}