package com.benediktvitek.ben.services;

import com.benediktvitek.ben.models.Comment;
import com.benediktvitek.ben.repositories.CommentRepository;
import com.benediktvitek.ben.repositories.CommentRespository;
import com.benediktvitek.ben.repositories.UserMessageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

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

    public List<>
}
