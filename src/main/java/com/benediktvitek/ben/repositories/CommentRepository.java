package com.benediktvitek.ben.repositories;

import com.benediktvitek.ben.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByUserEntityUsernameIgnoreCase(String name);

    List<Comment> findAllByDateBetween(Date startDate, Date endDate);
}