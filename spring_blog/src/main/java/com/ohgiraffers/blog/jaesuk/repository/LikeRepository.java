package com.ohgiraffers.blog.jaesuk.repository;

import com.ohgiraffers.blog.jaesuk.model.entity.JaesukBlog;
import com.ohgiraffers.blog.jaesuk.model.entity.JaesukComment;
import com.ohgiraffers.blog.jaesuk.model.entity.JaesukLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<JaesukLike, Long> {
    boolean existsByBlogAndUserIdAndLikeType(JaesukBlog blog, String userId, String likeType);
    void deleteByBlogAndUserIdAndLikeType(JaesukBlog blog, String userId, String likeType);
    long countByBlogAndLikeType(JaesukBlog blog, String likeType);

    boolean existsByCommentAndUserIdAndLikeType(JaesukComment comment, String userId, String likeType);
    void deleteByCommentAndUserIdAndLikeType(JaesukComment comment, String userId, String likeType);
    long countByCommentAndLikeType(JaesukComment comment, String likeType);
}