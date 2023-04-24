package com.example.osk.authentication;

import com.example.osk.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String secondName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dob;
    private Role role;
}

