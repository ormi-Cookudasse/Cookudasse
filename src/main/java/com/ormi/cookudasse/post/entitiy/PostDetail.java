package com.ormi.cookudasse.post.entitiy;


import com.ormi.cookudasse.post.dto.request.PostRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_detail_id")
    private Long postDetailId;

    private String postTitle;
    @Column(nullable = false)
    private int postView = 0;

    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;

    @Lob
    private String ingredients;

    @Lob
    private String recipe;

    private int postLike;

    public void addPostLike() {
        this.postLike++;
    }

    public void addPostView() {
        this.postView++;
    }

    public PostDetail updatePostDetail(PostRequest postRequest) {
        this.postTitle = postRequest.getPostTitle();
        this.foodCategory = postRequest.getFoodCategory();
        this.ingredients = postRequest.getIngredients();
        this.recipe = postRequest.getRecipe();
        return this;
    }

}
