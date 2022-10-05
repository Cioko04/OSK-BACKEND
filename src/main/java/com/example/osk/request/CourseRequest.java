package com.example.osk.request;

import com.example.osk.model.CategoryType;
import com.example.osk.model.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CourseRequest {
    private long id;
    private Date startDate;
    private Integer spendTimeInHours;
    private boolean isValid;
    private CategoryType categoryType;
    private Integer price;

    public CourseRequest() {
    }

    public CourseRequest(Course course){
        this.id = course.getId();
        this.startDate = course.getStartDate();
        this.spendTimeInHours = course.getSpendTimeInHours();
        this.isValid = course.isValid();
        this.categoryType = course.getCategory().getCategoryType();
        this.price = course.getCategory().getPrice();
    }
}
