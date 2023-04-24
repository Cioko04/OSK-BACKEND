package com.example.osk.commonUser;

import com.example.osk.user.Role;
import com.example.osk.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(
        name = "common_user"
)
public class CommonUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String secondName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate dob;
    @Transient
    private Integer age;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public CommonUser(CommonUserRequest userRequest) {
        this.name = userRequest.getName();
        this.secondName = userRequest.getSecondName();
        this.lastName = userRequest.getLastName();
        this.dob = userRequest.getDob();
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
}
