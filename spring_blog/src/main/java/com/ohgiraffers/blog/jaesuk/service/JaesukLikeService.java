package com.ohgiraffers.blog.jaesuk.service;

import com.ohgiraffers.blog.jaesuk.model.entity.JaesukBlog;
import com.ohgiraffers.blog.jaesuk.model.entity.JaesukComment;
import com.ohgiraffers.blog.jaesuk.model.entity.JaesukLike;
import com.ohgiraffers.blog.jaesuk.repository.LikeRepository;
import com.ohgiraffers.blog.jaesuk.repository.JaesukCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JaesukLikeService {

    private final LikeRepository likeRepository;
    private final JaesukCommentRepository jaesukCommentRepository;

    @Autowired
    public JaesukLikeService(LikeRepository likeRepository, JaesukCommentRepository jaesukCommentRepository) {
        this.likeRepository = likeRepository;
        this.jaesukCommentRepository = jaesukCommentRepository;
    }

    @Transactional
    public boolean togglePostLike(JaesukBlog blog, String userId) {
        if (likeRepository.existsByBlogAndUserIdAndLikeType(blog, userId, "POST")) {
            likeRepository.deleteByBlogAndUserIdAndLikeType(blog, userId, "POST");
            return false;
        } else {
            JaesukLike like = new JaesukLike(blog, userId);
            like.setLikeType("POST");
            likeRepository.save(like);
            return true;
        }
    }

    @Transactional
    public boolean toggleCommentLike(Long commentId, String userId) {
        JaesukComment comment = jaesukCommentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        if (likeRepository.existsByCommentAndUserIdAndLikeType(comment, userId, "COMMENT")) {
            likeRepository.deleteByCommentAndUserIdAndLikeType(comment, userId, "COMMENT");
            return false;
        } else {
            JaesukLike like = new JaesukLike(comment, userId);
            likeRepository.save(like);
            return true;
        }
    }

    public long getPostLikeCount(JaesukBlog blog) {
        return likeRepository.countByBlogAndLikeType(blog, "POST");
    }

    public long getCommentLikeCount(Long commentId) {
        JaesukComment comment = jaesukCommentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
        return likeRepository.countByCommentAndLikeType(comment, "COMMENT");
    }
}