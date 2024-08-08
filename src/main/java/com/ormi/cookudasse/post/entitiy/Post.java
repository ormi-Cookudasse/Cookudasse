package com.ormi.cookudasse.post.entitiy;

import com.ormi.cookudasse.auth.domain.User;
import com.ormi.cookudasse.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @Column(nullable = false)
    private int postView = 0;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_detail_id")
    private PostDetail postDetail;


    public void setImageUrl(String fileUrl) {

    }

    public Object getId() {
        return null;
    }
}

