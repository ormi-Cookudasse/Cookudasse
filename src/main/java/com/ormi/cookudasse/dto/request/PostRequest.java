package com.ormi.cookudasse.dto.request;

import com.ormi.cookudasse.entitiy.FoodCategory;
import com.ormi.cookudasse.entitiy.PostDetail;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class PostRequest {
    private String postTitle;
    private FoodCategory foodCategory;
    private String ingredients;
    private String recipe;
}
