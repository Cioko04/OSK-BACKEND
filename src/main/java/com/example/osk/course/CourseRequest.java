package com.example.osk.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private Long id;
    private BigDecimal price;
    private String description;
    private Long schoolId;
    private String categoryType;
    private Long studentCount;
    private Long instructorCount;

    public CourseRequest(Course course) {
        this.id = course.getId();
        this.price = course.getPrice();
        this.description = course.getDescription();
        this.schoolId = course.getSchool().getId();
        this.categoryType = course.getCategory().getCategoryType().getValue();
    }
}
