package com.example.osk.request;

import com.example.osk.model.CategoryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRequest {
    private long id;
    private CategoryType categoryType;
    private Integer price;
    private Integer time;

    public CategoryRequest(long id, CategoryType categoryType, Integer price, Integer time) {
        this.id = id;
        this.categoryType = categoryType;
        this.price = price;
        this.time = time;
    }
}
