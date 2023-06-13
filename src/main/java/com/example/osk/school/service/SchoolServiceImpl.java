package com.example.osk.school.service;

import com.example.osk.category.Category;
import com.example.osk.category.service.CategoryService;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public List<SchoolRequest> getSchools() {
        List<School> schools = schoolRepository.findAll();
        List<SchoolRequest> schoolRequests = new ArrayList<>();
        schools.forEach(school -> schoolRequests.add(createSchoolRequestWithUser(school)));
        return schoolRequests;
    }

    private SchoolRequest createSchoolRequestWithUser(School school) {
        SchoolRequest schoolRequest = new SchoolRequest(school);
        schoolRequest.setUserRequest(new UserRequest(school.getUser()));
        Set<String> categoryTypes = school.getCategories().stream().map(category -> category.getCategoryType().getValue()).collect(Collectors.toSet());
        schoolRequest.setCategories(categoryTypes);
        return schoolRequest;
    }

    @Override
    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "School with id " + id + " does not exist"));
    }

    @Override
    public void saveSchool(SchoolRequest schoolRequest) {
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
        schoolRepository.save(school);
    }

    @Override
    @Transactional
    public void updateSchool(SchoolRequest schoolRequest) {
        if (schoolRequest.getUserRequest() != null) {
            userService.updateUser(schoolRequest.getUserRequest());
        }

        School school = getSchoolById(schoolRequest.getId());

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
        if (!schoolRequest.getCategories().isEmpty()) {
            Set<String> categoriesFromSchool = school.getCategories().stream().map(category -> category.getCategoryType().getValue()).collect(Collectors.toSet());
            Set<String> categoriesFromRequest = schoolRequest.getCategories();
            Set<Category> missingCategories = categoryService.getUniqueValuesFromSecondList(categoriesFromSchool, categoriesFromRequest);
            missingCategories.forEach(school::addCategory);
        }
        schoolRepository.save(school);
    }

    @Override
    @Transactional
    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
