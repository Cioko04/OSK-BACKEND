package com.example.osk.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String name;
    private String secondName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dob;
    private Integer age;

    public UserRequest(Long id, String name, String secondName, String lastName, String email, String password, LocalDate dob, Integer age) {
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
