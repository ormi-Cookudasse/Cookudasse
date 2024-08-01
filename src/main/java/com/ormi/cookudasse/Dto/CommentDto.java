package com.ormi.cookudasse.Dto;

import com.ormi.cookudasse.Entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long post_id;
    private long user_id;
    private String comment_body;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int like_cnt;
    private int comment_cnt;

    //Entity => Dto 변환 정적 메소드
    public static CommentDto fromEntityToDto(Comment comment){
        return CommentDto.builder()
                .post_id(comment.getPost_id())
                .user_id(comment.getUser_id())
                .comment_body(comment.getComment_body())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .like_cnt(comment.getLike_cnt())
                .comment_cnt(comment.getComment_cnt())
                .build();
    }

    //Dto => Entity 변환 메소드
    public Comment toEntityFromDto(){
        return new Comment(
                this.post_id,
                this.user_id,
                this.comment_body,
                this.createdAt,
                this.updatedAt,
                this.like_cnt,
                this.comment_cnt
        );
    }
}
