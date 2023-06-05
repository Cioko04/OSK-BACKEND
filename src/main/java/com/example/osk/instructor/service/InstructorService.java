package com.example.osk.instructor.service;

import com.example.osk.category.CategoryType;
import com.example.osk.instructor.InstructorRequest;

import java.util.List;

public interface InstructorService {

    void saveInstructor(InstructorRequest instructorRequest);

    List<InstructorRequest> getInstructorsBySchoolId(Long id);

    void addCategoryToInstructor(Long instructorId, CategoryType categoryType);

    void deleteInstructorById(Long id);
}
