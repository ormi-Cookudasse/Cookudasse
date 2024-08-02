package com.ormi.cookudasse.post.dto.request;

import com.ormi.cookudasse.post.entitiy.FoodCategory;
import lombok.Data;

@Data
public class PostRequest {
    private String postTitle;
    private FoodCategory foodCategory;
    private String ingredients;
    private String recipe;
}
