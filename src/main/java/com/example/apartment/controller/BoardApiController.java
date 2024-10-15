package com.example.apartment.controller;

import com.example.apartment.config.auth.PrincipalDetail;
import com.example.apartment.dto.CommentRequestDto;
import com.example.apartment.dto.ResponseDto;
import com.example.apartment.model.Board;
import com.example.apartment.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;


    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.writePost(board, principalDetail.getUser());
        return new ResponseDto<>(HttpStatus.OK, 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.deletePost(id);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }
    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.updatePost(id, board);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }


    @PostMapping("/api/board/{boardid}/comment")
    public ResponseDto<Integer> save(@RequestBody CommentRequestDto comment){
        boardService.writeComment(comment);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }

    @DeleteMapping("/api/board/comment/{commentId}")
       public ResponseDto<Integer>delete(@PathVariable long commentId){
           boardService.deleteComment(commentId);
            return new ResponseDto<>(HttpStatus.OK, 1);
        }

}