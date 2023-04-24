package com.example.osk.commonUser;

import com.example.osk.user.UserRequest;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonUserRequest extends UserRequest {
    private Long id;
    private String name;
    private String secondName;
    private String lastName;
    private LocalDate dob;
    private Integer age;


    public CommonUserRequest(CommonUser commonUser){
        super(commonUser.getUser());
        this.id = commonUser.getId();
        this.name = commonUser.getName();
        this.secondName = commonUser.getSecondName();
        this.lastName = commonUser.getLastName();
        this.dob = commonUser.getDob();
        this.age = commonUser.getAge();
    }
}

