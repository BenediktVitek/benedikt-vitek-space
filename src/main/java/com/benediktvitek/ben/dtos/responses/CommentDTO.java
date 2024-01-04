package com.benediktvitek.ben.dtos.responses;

import com.benediktvitek.ben.models.Comment;

import java.util.Date;

public record CommentDTO(Long id, String author, String comment, String date) {

    public CommentDTO(Comment comment) {
        this(comment.getId(), comment.getUserEntity().getUsername(), comment.getMessage(), comment.getRegularDate());
    }
}
