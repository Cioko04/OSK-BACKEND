package com.example.osk.service;

import com.example.osk.model.Student;
import com.example.osk.request.StudentRequest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface StudentService {
    List<StudentRequest> getStudents();

    StudentRequest getStudent(Long id);

    Student saveStudent(Student student);

    void deleteStudent(Long id);

    @Transactional
    void updateStudent(Long id,
                          String name,
                          String secondName,
                          String lastName,
                          String email,
                          String password,
                          LocalDate dob);
}
