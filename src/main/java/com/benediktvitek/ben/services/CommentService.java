package com.benediktvitek.ben.services;

import com.benediktvitek.ben.dtos.responses.CommentDTO;
import com.benediktvitek.ben.models.Comment;
import com.benediktvitek.ben.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public boolean saveMessage(String comment, String author) throws BadRequestException {

        if (comment == null || comment.isEmpty() || comment.length() > 140) {
            throw new BadRequestException("Comment cannot be empty and can have max. 140 characters");
        } else if (author == null || author.isEmpty() || author.length() > 20) {
            throw new BadRequestException("Author cannot be empty and can have max. 20 characters");
        }
        commentRepository.save(new Comment(comment, author, false));
        return true;
    }

    public List<CommentDTO> getAllCommentsDto() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(comment -> new CommentDTO(comment))
                .collect(Collectors.toList());
    }

    public List<CommentDTO> getAllDtoByAuthor(String name) {
        List<Comment> comments = commentRepository.findAllByNameIgnoreCase(name);
        return comments.stream()
                .map(comment -> new CommentDTO(comment))
                .collect(Collectors.toList());
    }

    public Optional<Comment> getById(Long id) {
        return commentRepository.findById(id);
    }

    public List<CommentDTO> getAllDtoFromToday() {
        Date today = new Date(System.currentTimeMillis());
        List<Comment> comments = commentRepository.findAllByDateBetween(today, today);
        return comments.stream()
                .map(comment -> new CommentDTO(comment))
                .collect(Collectors.toList());
    }
}
