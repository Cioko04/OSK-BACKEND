package com.example.osk.school.service;

import com.example.osk.school.School;
import com.example.osk.school.SchoolRequest;

import java.util.List;
import java.util.Set;

public interface SchoolService {

    List<SchoolRequest> getSchools();

    School getSchoolById(Long id);

    Set<SchoolRequest> getSchoolsByCitiesAndCategories(Set<String> cities, Set<String> categories);

    void saveSchool(SchoolRequest schoolRequest);

    void updateSchool(SchoolRequest schoolRequest);

    void deleteSchool(Long id);

    Set<String> getCities();

}
