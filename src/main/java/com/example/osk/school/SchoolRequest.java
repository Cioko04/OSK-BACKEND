package com.example.osk.school;

import com.example.osk.user.User;
import com.example.osk.user.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchoolRequest {
    private String schoolName;
    private String owner;
    private String city;
    private String zipCode;
    private String nip;
    private LocalDate addDate;
    private UserRequest userRequest;

    public SchoolRequest(School school) {
        this.schoolName = school.getSchoolName();
        this.owner = school.getOwner();
        this.city = school.getCity();
        this.zipCode = school.getZipCode();
        this.nip = school.getNip();
        this.addDate = school.getAddDate();
    }
}
