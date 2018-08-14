package com.example.demo.controllers.sockets;

import com.example.demo.dto.CommentRequest;
import com.example.demo.model.Comment;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {

    @MessageMapping("/com")
    @SendTo("/comment/{id}")
    public Comment send(CommentRequest dto) {
        Long issueId = dto.getIssueId();
        return new Comment();
    }

}
