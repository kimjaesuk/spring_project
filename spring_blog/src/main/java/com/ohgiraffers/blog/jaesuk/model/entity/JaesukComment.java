package com.ohgiraffers.blog.jaesuk.model.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jaesuk_comment")
public class JaesukComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_no")
    private JaesukBlog blog;

    @Column(nullable = false)
    private long likeCount = 0;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JaesukLike> likes = new ArrayList<>();


    // 기본 생성자
    public JaesukComment() {
    }

    // 생성자
    public JaesukComment(String author, String content) {
        this.author = author;
        this.content = content;
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public JaesukBlog getBlog() {
        return blog;
    }

    public void setBlog(JaesukBlog blog) {
        this.blog = blog;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public List<JaesukLike> getLikes() {
        return likes;
    }

    public void setLikes(List<JaesukLike> likes) {
        this.likes = likes;
    }

    // 좋아요 추가 메서드
    public void addLike(JaesukLike like) {
        likes.add(like);
        like.setComment(this);
        this.likeCount++;
    }

    // 좋아요 제거 메서드
    public void removeLike(JaesukLike like) {
        likes.remove(like);
        like.setComment(null);
        this.likeCount = Math.max(0, this.likeCount - 1);
    }

    // toString 메서드
    @Override
    public String toString() {
        return "JaesukComment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", likeCount=" + likeCount +
                '}';
    }
}