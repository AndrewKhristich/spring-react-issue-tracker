package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "issue_comments", schema = "public")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "publish_date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date publishedAt;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    @Size(max = 20)
    @NotNull
    private String author;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "issue_id")
    private Issue issue;

    public Comment() {
        this.publishedAt = new Date();
    }

    public Comment(String description, String status) {
        this.description = description;
        this.status = status;
        this.publishedAt = new Date();
    }
}
