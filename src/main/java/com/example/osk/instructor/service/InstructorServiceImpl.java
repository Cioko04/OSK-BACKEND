package com.example.osk.instructor.service;

import com.example.osk.category.Category;
import com.example.osk.category.service.CategoryService;
import com.example.osk.instructor.Instructor;
import com.example.osk.instructor.InstructorRequest;
import com.example.osk.instructor.repository.InstructorRepository;
import com.example.osk.school.School;
import com.example.osk.school.service.SchoolService;
import com.example.osk.user.Role;
import com.example.osk.user.User;
import com.example.osk.user.UserRequest;
import com.example.osk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final SchoolService schoolService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public List<InstructorRequest> getInstructorsBySchoolId(Long id) {
        Set<Instructor> instructors = instructorRepository.findAllBySchoolId(id);
        return instructors.stream().map(InstructorRequest::new).toList();
    }

    @Override
    public void updateInstructor(InstructorRequest instructorRequest) {
        if (instructorRequest.getUserRequest() != null) {
            userService.updateUser(instructorRequest.getUserRequest());
        }

        Instructor instructor = getInstructorById(instructorRequest.getId());

        if (!instructorRequest.getCategories().isEmpty()) {
            Set<String> categoriesFromInstructor = instructor.getCategories().stream().map(category -> category.getCategoryType().getValue()).collect(Collectors.toSet());
            Set<String> categoriesFromRequest = instructorRequest.getCategories();
            Set<Category> missingCategories = categoryService.getUniqueValuesFromSecondList(categoriesFromInstructor, categoriesFromRequest);
            missingCategories.forEach(instructor::addCategory);
        }
        instructorRepository.save(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Instructor with id " + id + " does not exist"));
    }

    @Override
    public void saveInstructor(InstructorRequest instructorRequest) {
        School school = schoolService.getSchoolById(instructorRequest.getSchoolId());
        UserRequest userRequest = instructorRequest.getUserRequest();
        userRequest.setRole(Role.INSTRUCTOR);
        User user = userService.saveUser(userRequest);
        Instructor instructor = Instructor.builder()
                .school(school)
                .user(user)
                .build();
        instructorRepository.save(instructor);
    }

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepository.deleteById(id);
    }

}
