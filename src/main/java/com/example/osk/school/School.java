package com.example.osk.school;

import com.example.osk.course.Course;
import com.example.osk.instructor.Instructor;
import com.example.osk.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(
        name = "SCHOOLS"
)
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String schoolName;
    @NotNull
    private String city;
    @NotNull
    private String zipCode;
    @NotNull
    private String nip;
    @NotNull
    private LocalDate addDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school", cascade = CascadeType.ALL)
    private Set<Instructor> instructors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school", cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    public School(SchoolRequest schoolRequest) {
        this.schoolName = schoolRequest.getSchoolName();
        this.city = schoolRequest.getCity();
        this.zipCode = schoolRequest.getZipCode();
        this.nip = schoolRequest.getNip();
        this.addDate = schoolRequest.getAddDate();
    }
}
