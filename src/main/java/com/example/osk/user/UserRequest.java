package com.example.osk.user;

import com.example.osk.authentication.RegisterRequest;
import com.example.osk.school.SchoolRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String secondName;
    private String lastName;
    private LocalDate dob;
    private Integer age;
    private Role role;
    private SchoolRequest schoolRequest;

    public UserRequest(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.secondName = user.getSecondName();
        this.lastName = user.getLastName();
        this.dob = user.getDob();
        this.role = user.getRole();
        this.age = user.getAge();
    }

    public UserRequest(RegisterRequest request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.name = request.getName();
        this.secondName = request.getSecondName();
        this.lastName = request.getLastName();
        this.dob = request.getDob();
        this.role = Role.USER;
    }
}
