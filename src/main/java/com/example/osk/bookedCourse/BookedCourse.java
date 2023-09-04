package com.example.osk.bookedCourse;

import com.example.osk.category.Category;
import com.example.osk.course.Course;
import com.example.osk.instructor.Instructor;
import com.example.osk.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(
        name = "BOOKED_COURSES"
)
public class BookedCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private int timeConsumed;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
