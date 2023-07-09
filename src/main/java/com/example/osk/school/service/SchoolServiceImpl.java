package com.example.osk.school.service;

import com.example.osk.category.Category;
import com.example.osk.category.CategoryType;
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
import java.util.*;
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
        return schoolRequest;
    }

    @Override
    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "School with id " + id + " does not exist"));
    }

    @Override
    public Set<SchoolRequest> getSchoolsByCitiesAndCategories(Set<String> cities, Set<String> categories) {
        Set<CategoryType> categoryTypes = CategoryType.getCategoriesFromString(categories);
        Set<School> schools = schoolRepository.findDistinctByCityInAndCategoriesCategoryTypeIn(cities, categoryTypes);
        Set<SchoolRequest> schoolRequests = new HashSet<>();

        schools.forEach(school -> schoolRequests.add(new SchoolRequest(school)));
        return schoolRequests;
    }

    @Override
    public void saveSchool(SchoolRequest schoolRequest) {
        UserRequest userRequest = schoolRequest.getUserRequest();
        userRequest.setRole(Role.OSK_ADMIN);
        User user = userService.saveUser(userRequest);
        Set<Category> categories = schoolRequest.getCategories().stream()
                .map(category -> categoryService.getCategory(CategoryType.getCategoryTypeFromString(category)))
                .collect(Collectors.toSet());

        School school = School.builder()
                .schoolName(schoolRequest.getSchoolName())
                .city(schoolRequest.getCity())
                .zipCode(schoolRequest.getZipCode())
                .nip(schoolRequest.getNip())
                .addDate(LocalDate.now())
                .user(user)
                .categories(categories)
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

        Set<Category> updatedCategories = categoryService.getCategoriesFromStringList(schoolRequest.getCategories());
        school.setCategories(updatedCategories);

        schoolRepository.save(school);
    }

    @Override
    @Transactional
    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public Set<String> getCities() {
        return schoolRepository.getAllCities();
    }
}
