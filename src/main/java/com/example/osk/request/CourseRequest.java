package com.example.osk.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequest {
    private long id;
    private long studentId;
    private long categoryId;
    private Date startDate;
    private Integer spendTimeInHours;
    private boolean isValid;

    public CourseRequest(long id, long studentId, long categoryId, Date startDate, Integer spendTimeInHours, boolean isValid) {
        this.id = id;
        this.studentId = studentId;
        this.categoryId = categoryId;
        this.startDate = startDate;
        this.spendTimeInHours = spendTimeInHours;
        this.isValid = isValid;
    }
}
