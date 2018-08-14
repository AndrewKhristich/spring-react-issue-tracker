package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "issues", schema = "public")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "issue_name")
    @Size(min = 4, max = 30)
    private String issueName;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    @Size(min = 10, max = 50)
    private String description;

    @Column(name = "publish_date")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date publishedAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private User user;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "issue")
    private List<Comment> comments = new ArrayList<>();

    public Issue() {
        this.publishedAt = new Date();
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", issueName='" + issueName + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
