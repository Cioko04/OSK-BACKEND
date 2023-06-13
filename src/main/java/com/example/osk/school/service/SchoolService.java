package com.example.osk.school.service;

import com.example.osk.school.School;
import com.example.osk.school.SchoolRequest;

import java.util.List;

public interface SchoolService {

    List<SchoolRequest> getSchools();

    School getSchoolById(Long id);

    void saveSchool(SchoolRequest schoolRequest);

    void updateSchool(SchoolRequest schoolRequest);

    void deleteSchool(Long id);

}
