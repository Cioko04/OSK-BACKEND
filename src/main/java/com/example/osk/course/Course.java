package com.example.osk.course;

import com.example.osk.bookedCourse.BookedCourse;
import com.example.osk.category.Category;
import com.example.osk.school.School;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(
        name = "COURSES",
        uniqueConstraints = @UniqueConstraint(columnNames = {"school_id", "category_id"})
)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL)
    private Set<BookedCourse> bookedCourses = new HashSet<>();
}
