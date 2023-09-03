package com.example.osk.school;

import com.example.osk.user.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolRequest {
    private Long id;
    private String schoolName;
    private String city;
    private String zipCode;
    private String nip;
    private LocalDate addDate;
    private UserRequest userRequest;
    private Set<String> categories;

    public SchoolRequest(School school) {
        this.id = school.getId();
        this.schoolName = school.getSchoolName();
        this.city = school.getCity();
        this.zipCode = school.getZipCode();
        this.nip = school.getNip();
        this.addDate = school.getAddDate();
    }
}
