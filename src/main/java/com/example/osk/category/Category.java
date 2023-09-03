package com.example.osk.category;

import com.example.osk.course.Course;
import com.example.osk.instructor.Instructor;
import com.example.osk.school.School;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(
        name = "categories",
        uniqueConstraints = @UniqueConstraint(
        name = "type_unique",
        columnNames = "category_type"
)
)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int duration;
    @Enumerated(EnumType.STRING)
    @Column(name = "category_type")
    private CategoryType categoryType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<Instructor> instructors = new HashSet<>();
}
