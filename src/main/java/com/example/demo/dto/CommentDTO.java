package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long issueId;
    private String description;
    private String status;
}
