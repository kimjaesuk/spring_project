package com.ohgiraffers.blog.jooyeon.model.dto;

import com.ohgiraffers.blog.jinhee.model.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class BlogDTO {

    private String blogTitle;
    private String blogContent;
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String registerDate;

    public BlogDTO() {
    }

    public BlogDTO(String blogTitle, String blogContent, User user, String registerDate) {
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.user = user;
        this.registerDate = registerDate;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "BlogDTO{" +
                "blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", user=" + user +
                ", registerDate='" + registerDate + '\'' +
                '}';
    }

    public void setId(String s) {
    }

    public void setCreateDate(Date createDate) {
    }

    public Integer getId() {
        return id; // getId() 메서드가 id 필드를 반환하도록 수정
    }

    public void setId(Integer id) {
        this.id = id; // setId() 메서드가 id 필드를 설정하도록 수정
    }


}

