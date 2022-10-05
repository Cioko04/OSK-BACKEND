package com.example.osk.request;

import com.example.osk.model.CategoryType;
import com.example.osk.model.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryRequest {
    private long id;
    private CategoryType categoryType;
    private Integer price;
    private Integer time;
    List<CourseRequest> courses = new ArrayList<>();

    public CategoryRequest() {
    }

    public CategoryRequest(long id, CategoryType categoryType, Integer price, Integer time) {
        this.id = id;
        this.categoryType = categoryType;
        this.price = price;
        this.time = time;
    }
}
