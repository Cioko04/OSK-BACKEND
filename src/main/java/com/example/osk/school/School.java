package com.example.osk.school;

import com.example.osk.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(
        name = "schools"
)
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String schoolName;
    @NotNull
    private String city;
    @NotNull
    private String zipCode;
    @NotNull
    private String nip;
    @NotNull
    private LocalDate addDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public School(SchoolRequest schoolRequest) {
        this.schoolName = schoolRequest.getSchoolName();
        this.city = schoolRequest.getCity();
        this.zipCode = schoolRequest.getZipCode();
        this.nip = schoolRequest.getNip();
        this.addDate = schoolRequest.getAddDate();
    }
}
