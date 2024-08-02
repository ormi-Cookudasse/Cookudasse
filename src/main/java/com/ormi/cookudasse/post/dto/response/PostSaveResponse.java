package com.ormi.cookudasse.post.dto.response;

import com.ormi.cookudasse.post.entitiy.PostDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostSaveResponse {
    private Long id;
    private PostDetail postDetail;
    private LocalDateTime createdAt;
    private Long user_id;

    public PostSaveResponse(Object id) {

    }
}
