package com.example.demo.controllers.sockets;

import com.example.demo.dto.CommentDTO;
import com.example.demo.model.Comment;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentController {

    @MessageMapping("/com")
    @SendTo("/comment/{id}")
    public Comment send(CommentDTO dto) {
        Long issueId = dto.getIssueId();
        return new Comment();
    }

}
