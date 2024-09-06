package com.example.apartment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class CommentRequestDto {

    private String content;
    private long userId;
    private long boardId;

}