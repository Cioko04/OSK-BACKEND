package com.example.osk.request;

import com.example.osk.model.Course;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class StudentRequest {
    private Long id;
    private String name;
    private String secondName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dob;
    private Integer age;
    private List<CourseRequest> course;

    public StudentRequest() {
    }

    public StudentRequest(Long id, String name, String secondName, String lastName, String email, String password, LocalDate dob, Integer age) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.age = age;

    }
}
