package com.benediktvitek.ben.services;

import com.benediktvitek.ben.dtos.responses.CommentDTO;
import com.benediktvitek.ben.models.Comment;
import com.benediktvitek.ben.models.UserEntity;
import com.benediktvitek.ben.repositories.CommentRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserEntityService userEntityService;

    public CommentService(CommentRepository commentRepository, UserEntityService userEntityService) {
        this.commentRepository = commentRepository;
        this.userEntityService = userEntityService;
    }

    public boolean saveMessage(String message) throws BadRequestException {

        if (message == null || message.isEmpty() || message.length() > 140) {
            throw new BadRequestException("Comment cannot be empty and can have max. 140 characters");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loggedUser;
        Comment comment;

        if (!authentication.getName().equals("anonymousUser")) {
            loggedUser = userEntityService.getByName(authentication.getName());
        } else {
            loggedUser = userEntityService.getByName("anonymous");
        }
        comment = new Comment(message, loggedUser);

        loggedUser.getComments().add(comment);
        userEntityService.save(loggedUser);
        commentRepository.save(comment);
        return true;
    }

    public List<CommentDTO> getAllCommentsDto() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(comment -> new CommentDTO(comment))
                .collect(Collectors.toList());
    }

    public List<CommentDTO> getAllDtoByAuthor(String name) {
        List<Comment> comments = commentRepository.findAllByUserEntityUsernameIgnoreCase(name);
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
