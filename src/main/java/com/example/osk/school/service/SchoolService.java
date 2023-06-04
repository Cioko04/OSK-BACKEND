package com.example.osk.school.service;

import com.example.osk.school.School;
import com.example.osk.school.SchoolRequest;
import com.example.osk.user.UserRequest;

import java.util.List;
import java.util.Optional;

public interface SchoolService {

    List<SchoolRequest> getSchools();

    Optional<School> getSchoolById(Long id);

    SchoolRequest getSchool(String email);

    List<UserRequest> getInstructors(Long id);

    School saveSchool(SchoolRequest schoolRequest);

    void updateSchool(SchoolRequest schoolRequest);

    void deleteSchool(Long id);

}
