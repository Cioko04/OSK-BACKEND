package com.example.osk.instructor.service;

import com.example.osk.category.CategoryType;
import com.example.osk.instructor.Instructor;
import com.example.osk.instructor.InstructorRequest;

import java.util.List;
import java.util.Optional;

public interface InstructorService {

    Instructor getInstructorById(Long id);

    void saveInstructor(InstructorRequest instructorRequest);

    List<InstructorRequest> getInstructorsBySchoolId(Long id);

    void addCategoryToInstructor(Long instructorId, CategoryType categoryType);

    void deleteInstructorById(Long id);
}
