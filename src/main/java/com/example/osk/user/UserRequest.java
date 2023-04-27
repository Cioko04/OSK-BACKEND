package com.example.osk.user;

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
    private Role role;
    private String name;
    private String secondName;
    private String lastName;
    private LocalDate dob;
    private Integer age;


    public UserRequest(User user){
        this.id = user.getId();;
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.name = user.getName();
        this.secondName = user.getSecondName();
        this.lastName = user.getLastName();
        this.dob = user.getDob();
        this.age = user.getAge();
    }
}
