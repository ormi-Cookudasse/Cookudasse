package com.ormi.cookudasse.post.repository;


import com.ormi.cookudasse.post.entitiy.PostDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDetailRepository  extends JpaRepository<PostDetail, Long> {
}
