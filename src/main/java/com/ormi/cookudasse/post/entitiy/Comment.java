package com.ormi.cookudasse.post.entitiy;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;
    private String author;
    private LocalDateTime createdAt;

    public void setPost(Post post) {

    }

    public void setAuthor(String author) {

    }

    public void setContent(String content) {

    }

    public void setCreatedAt(LocalDateTime now) {


    }

    // 생성자, getter, setter 등
}
