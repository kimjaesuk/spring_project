package com.ohgiraffers.blog.jaesuk.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jaesuk_like",
        uniqueConstraints = @UniqueConstraint(columnNames = {"blog_no", "comment_id", "user_id", "like_type"}))
public class JaesukLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_no")
    private JaesukBlog blog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private JaesukComment comment;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "like_type", nullable = false)
    private String likeType; // "POST" or "COMMENT"

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public JaesukLike() {
        this.createdAt = LocalDateTime.now();
    }

    public JaesukLike(JaesukBlog blog, String userId) {
        this();
        this.blog = blog;
        this.userId = userId;
        this.likeType = "POST";
    }

    public JaesukLike(JaesukComment comment, String userId) {
        this();
        this.comment = comment;
        this.userId = userId;
        this.likeType = "COMMENT";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public JaesukBlog getBlog() { return blog; }
    public void setBlog(JaesukBlog blog) { this.blog = blog; }

    public JaesukComment getComment() { return comment; }
    public void setComment(JaesukComment comment) { this.comment = comment; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getLikeType() { return likeType; }
    public void setLikeType(String likeType) { this.likeType = likeType; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "JaesukLike{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", likeType='" + likeType + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}