package com.benediktvitek.ben.dtos.responses;

import com.benediktvitek.ben.models.Comment;

import java.util.Date;

public record CommentDTO(String author, String comment, String date) {

    public CommentDTO(Comment comment) {
        this(comment.getName(), comment.getMessage(), comment.getRegularDate());
    }
}
