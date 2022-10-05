package com.example.osk.model;


import com.example.osk.request.StudentRequest;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "students",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email"
        )
)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String secondName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private LocalDate dob;
    @Transient
    private Integer age;
    @OneToMany(mappedBy = "student")
    List<Course> courses = new ArrayList<>();

    public Student(StudentRequest studentRequest){
        this.name = studentRequest.getName();
        this.secondName = studentRequest.getSecondName();
        this.lastName = studentRequest.getLastName();
        this.email = studentRequest.getEmail();
        this.password = studentRequest.getPassword();
        this.dob = studentRequest.getDob();
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

}
