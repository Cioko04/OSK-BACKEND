package com.example.osk.school.service;

import com.example.osk.school.SchoolRequest;

import java.util.List;

public interface SchoolService {

    List<SchoolRequest> getSchools();

    SchoolRequest getSchool(String email);

    void saveSchool(SchoolRequest schoolRequest);

}
