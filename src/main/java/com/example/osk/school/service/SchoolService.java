package com.example.osk.school.service;

import com.example.osk.school.School;
import com.example.osk.school.SchoolRequest;

import java.util.List;

public interface SchoolService {

    List<SchoolRequest> getSchools();

    SchoolRequest getSchool(String email);

    School saveSchool(School school);

    void updateSchool(Long id, SchoolRequest schoolRequest);

    void deleteSchool(Long id);

}
