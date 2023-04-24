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


    public UserRequest(User user){
        this.id = user.getId();;
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
