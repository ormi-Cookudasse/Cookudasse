package com.ormi.cookudasse.post.repository;

import com.ormi.cookudasse.post.entitiy.Comment;
import com.ormi.cookudasse.post.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostOrderByCreatedAtDesc(Post post);
}