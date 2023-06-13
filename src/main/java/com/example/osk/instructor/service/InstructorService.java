package com.example.osk.instructor.service;

import com.example.osk.instructor.Instructor;
import com.example.osk.instructor.InstructorRequest;

import java.util.List;

public interface InstructorService {

    Instructor getInstructorById(Long id);

    void saveInstructor(InstructorRequest instructorRequest);

    List<InstructorRequest> getInstructorsBySchoolId(Long id);

    void updateInstructor(InstructorRequest instructorRequest);

    void deleteInstructorById(Long id);
}
