package com.example.osk.user;


import com.example.osk.course.Course;
import com.example.osk.instructor.Instructor;
import com.example.osk.school.School;
import com.example.osk.token.Token;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email"
        )
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @NotNull
    private String name;
    private String secondName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate dob;
    @Transient
    private Integer age;

    @OneToOne(mappedBy = "user")
    private School school;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Token> tokens = new HashSet<>();

    public User(UserRequest userRequest) {
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.name = userRequest.getName();
        this.secondName = userRequest.getSecondName();
        this.lastName = userRequest.getLastName();
        this.dob = userRequest.getDob();
        this.role = userRequest.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
}
