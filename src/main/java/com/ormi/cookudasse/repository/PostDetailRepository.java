package com.ormi.cookudasse.repository;


import com.ormi.cookudasse.entitiy.PostDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDetailRepository  extends JpaRepository<PostDetail, Long> {
}
