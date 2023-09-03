package com.example.osk.school.service;

import com.example.osk.school.School;
import com.example.osk.school.SchoolRequest;

import java.util.List;
import java.util.Set;

public interface SchoolService {

    List<SchoolRequest> getSchools();

    List<SchoolRequest> getSchoolsWithCategories();

    School getSchoolById(Long id);

    void saveSchool(SchoolRequest schoolRequest);

    void updateSchool(SchoolRequest schoolRequest);

    void deleteSchool(Long id);

    Set<String> getCities();

}
