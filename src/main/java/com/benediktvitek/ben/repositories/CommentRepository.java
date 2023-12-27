package com.benediktvitek.ben.repositories;

import com.benediktvitek.ben.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}