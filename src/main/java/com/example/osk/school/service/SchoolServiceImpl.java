package com.example.osk.school.service;

import com.example.osk.school.School;
import com.example.osk.school.SchoolRequest;
import com.example.osk.school.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;

    @Override
    public List<SchoolRequest> getSchools() {
        List<School> schools = schoolRepository.findAll();
        List<SchoolRequest> schoolRequests = new ArrayList<>();
        schools.forEach(school -> schoolRequests.add(new SchoolRequest(school)));
        return schoolRequests;
    }

    @Override
    public SchoolRequest getSchool(String email) {
        return null;
    }

    @Override
    public School saveSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public void updateSchool(Long id, SchoolRequest schoolRequest) {

        School school = schoolRepository.findById(schoolRequest.getId()).orElseThrow(() -> new IllegalStateException(
                "User with id " + schoolRequest.getId() + " does not exist"));

        if (schoolRequest.getSchoolName() != null &&
                schoolRequest.getSchoolName().length() > 0 &&
                !Objects.equals(school.getSchoolName(), schoolRequest.getSchoolName())) {
            school.setSchoolName(schoolRequest.getSchoolName());
        }
        if (schoolRequest.getCity() != null &&
                schoolRequest.getCity().length() > 0 &&
                !Objects.equals(school.getCity(), schoolRequest.getCity())) {
            school.setCity(schoolRequest.getCity());
        }
        if (schoolRequest.getZipCode() != null &&
                schoolRequest.getZipCode().length() > 0 &&
                !Objects.equals(school.getZipCode(), schoolRequest.getZipCode())) {
            school.setZipCode(schoolRequest.getZipCode());
        }
        if (schoolRequest.getNip() != null &&
                schoolRequest.getNip().length() > 0 &&
                !Objects.equals(school.getNip(), schoolRequest.getNip())) {
            school.setNip(schoolRequest.getNip());
        }
        schoolRepository.save(school);
    }

    @Override
    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
