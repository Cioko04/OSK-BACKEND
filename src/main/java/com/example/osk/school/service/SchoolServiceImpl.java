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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService{
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
    public SchoolRequest getSchool(String email) {
        return null;
    }

    @Override
    @Transactional
    public void saveSchool(SchoolRequest schoolRequest) {
        UserRequest userRequest = schoolRequest.getUserRequest();
        userRequest.setRole(Role.OSK_ADMIN);
        User user = userService.saveUser(userRequest);

        School school = new School(schoolRequest);
        school.setUser(user);
        schoolRepository.save(school);
    }
}
