package com.example.osk.course;

import com.example.osk.category.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private Long id;
    private LocalDate startDate;
    private Long userId;
    private CategoryType categoryType;
    private Long instructorId;
}
