package com.benediktvitek.ben.services;

import com.benediktvitek.ben.models.UserMessage;
import com.benediktvitek.ben.repositories.UserMessageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserMessageRepository userMessageRepository;

    public boolean saveMessage(String comment, String author) throws BadRequestException {

        if (comment == null || comment.isEmpty() || comment.length() > 140) {
            throw new BadRequestException("Comment cannot be empty and can have max. 140 characters");
        } else if (author == null || author.isEmpty() || author.length() > 20) {
            throw new BadRequestException("Author cannot be empty and can have max. 20 characters");
        }

        userMessageRepository.save(new UserMessage(comment, author));
        return true;
    }
}
