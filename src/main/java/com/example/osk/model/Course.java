package com.example.osk.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(
        name = "courses"
)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    private Integer spendTimeInHours;
    @Transient
    private boolean isValid;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public boolean isValid() {
        return category.getTime() > spendTimeInHours;
    }
}
