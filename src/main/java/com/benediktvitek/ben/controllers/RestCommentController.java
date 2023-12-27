package com.benediktvitek.ben.controllers;

import com.benediktvitek.ben.dtos.errors.ErrorDTO;
import com.benediktvitek.ben.dtos.responses.CommentDTO;
import com.benediktvitek.ben.dtos.responses.ListOfCommentsDTO;
import com.benediktvitek.ben.models.Comment;
import com.benediktvitek.ben.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class RestCommentController {

    private final CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<CommentDTO> comments = commentService.getAllCommentsDto();

        if(comments.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorDTO("No comments found"));
        } else {
            return ResponseEntity.ok(new ListOfCommentsDTO(comments));
        }
    }

    @GetMapping("/users/{author}")
    public ResponseEntity<?> getAllByName(@PathVariable String author) {
        List<CommentDTO> comments = commentService.getAllDtoByAuthor(author);

        if(comments.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorDTO("No comments by " + author.toUpperCase() + " found"));
        } else {
            return ResponseEntity.ok(new ListOfCommentsDTO(comments));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllByName(@PathVariable Long id) {
       Optional<Comment> comment = commentService.getById(id);

       if(comment.isEmpty()) {
           return ResponseEntity.badRequest().body(new ErrorDTO("No comment of ID: " + id));
       } else {
           return ResponseEntity.ok(new CommentDTO(comment.get()));
       }
    }
}
