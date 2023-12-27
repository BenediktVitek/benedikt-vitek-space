package com.benediktvitek.ben.controllers;

import com.benediktvitek.ben.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestCommentController {

    private final CommentService commentService;



}
