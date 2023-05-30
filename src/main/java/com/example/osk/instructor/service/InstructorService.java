package com.example.osk.instructor.service;

import com.example.osk.instructor.Instructor;
import com.example.osk.instructor.InstructorRequest;

import java.util.List;

public interface InstructorService {

    Instructor saveInstructor(InstructorRequest instructorRequest);

    List<InstructorRequest> getInstructorsBySchoolId(Long id);
}
