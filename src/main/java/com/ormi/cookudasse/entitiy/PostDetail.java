package com.ormi.cookudasse.entitiy;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_detail_id")
    private Long postDetailId;

    private String postTitle;

    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;

    @Lob
    private String ingredients;

    @Lob
    private String recipe;

    private int postLike;
    private int postView;
}
