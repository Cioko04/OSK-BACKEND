package com.example.osk.school.service;

import com.example.osk.school.School;
import com.example.osk.school.SchoolRequest;
import com.example.osk.school.repository.SchoolRepository;
import com.example.osk.user.Role;
import com.example.osk.user.User;
import com.example.osk.user.UserRequest;
import com.example.osk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final UserService userService;

    @Override
    public List<SchoolRequest> getSchools() {
        List<School> schools = schoolRepository.findAll();
        List<SchoolRequest> schoolRequests = new ArrayList<>();
        schools.forEach(school -> schoolRequests.add(new SchoolRequest(school)));
        return schoolRequests;
    }

    @Override
    public Optional<School> getSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    @Override
    public SchoolRequest getSchool(String email) {
        return null;
    }

    @Override
    public List<UserRequest> getInstructors(Long id) {
        return null;
    }

    @Override
    public School saveSchool(SchoolRequest schoolRequest) {
        UserRequest userRequest = schoolRequest.getUserRequest();
        userRequest.setRole(Role.OSK_ADMIN);
        User user = userService.saveUser(userRequest);

        School school = School.builder()
                .schoolName(schoolRequest.getSchoolName())
                .city(schoolRequest.getCity())
                .zipCode(schoolRequest.getZipCode())
                .nip(schoolRequest.getNip())
                .addDate(LocalDate.now())
                .user(user)
                .build();
        return schoolRepository.save(school);
    }

    @Override
    public void updateSchool(SchoolRequest schoolRequest) {
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
    @Transactional
    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
