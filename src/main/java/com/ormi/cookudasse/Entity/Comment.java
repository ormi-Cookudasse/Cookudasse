package com.ormi.cookudasse.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @Column(nullable = false)       //어떤 게시글의 댓글인지 봐야됨으로 null X
    private Long post_id;

    @Column(nullable = false)       //누가 썼는지 확인해야 하니까 null X
    private long user_id;

    @Column(nullable = false)       //본문에 해당하는 내용이 있어야 하므로 null X
    private String comment_body;

    @Column(nullable = false)       //생성시점을 알아야해서 null X
    private LocalDateTime createdAt;

    @Column(nullable = false)       //업데이트 시간 알아야해서 null X
    private LocalDateTime updatedAt;

    @Column(nullable = false)       //
    private int like_cnt;

    @Column(nullable = false)
    private int comment_cnt;

    public Comment(Long postId, long userId, String commentBody, LocalDateTime createdAt, LocalDateTime updatedAt, int likeCnt, int commentCnt) {
    }
}
