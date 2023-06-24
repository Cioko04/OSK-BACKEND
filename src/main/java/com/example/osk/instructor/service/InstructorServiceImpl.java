package com.example.osk.instructor.service;

import com.example.osk.category.Category;
import com.example.osk.category.CategoryType;
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

        Set<Category> updatedCategories = categoryService.getCategoriesFromStringList(instructorRequest.getCategories());
        instructor.setCategories(updatedCategories);

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
        Set<Category> categories = instructorRequest.getCategories().stream()
                .map(category -> categoryService.getCategory(CategoryType.getCategoryTypeFromString(category)))
                .collect(Collectors.toSet());

        Instructor instructor = Instructor.builder()
                .school(school)
                .user(user)
                .categories(categories)
                .build();
        instructorRepository.save(instructor);
    }

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepository.deleteById(id);
    }

}
