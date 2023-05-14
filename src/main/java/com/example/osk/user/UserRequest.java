package com.example.osk.user;

import com.example.osk.school.SchoolRequest;
import lombok.*;

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
    private SchoolRequest schoolRequest;


    public UserRequest(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.secondName = user.getSecondName();
        this.lastName = user.getLastName();
        this.dob = user.getDob();
        this.age = user.getAge();
        if (user.getSchool() != null) {
            this.schoolRequest = new SchoolRequest(user.getSchool());
        }
    }
}
