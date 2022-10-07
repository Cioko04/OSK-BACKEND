package com.example.osk.service;

import com.example.osk.model.Student;
import com.example.osk.repository.StudentRepository;
import com.example.osk.request.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    @Override
    public List<StudentRequest> getStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentRequest> studentRequestList = new ArrayList<>();
        studentList.forEach(student -> {
            StudentRequest studentRequest = new StudentRequest(
                    student.getId(),
                    student.getName(),
                    student.getSecondName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getPassword(),
                    student.getDob(),
                    student.getAge()
            );
            studentRequestList.add(studentRequest);
        });
        return studentRequestList;
    }

    @Override
    public StudentRequest getStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + " does not exist"));
        return new StudentRequest(
                student.getId(),
                student.getName(),
                student.getSecondName(),
                student.getLastName(),
                student.getEmail(),
                student.getPassword(),
                student.getDob(),
                student.getAge()
        );
    }

    private Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + " does not exist"));
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }


    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        courseService.deleteCoursesByStudent(student);
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateStudent(Long id,
                              String name,
                              String secondName,
                              String lastName,
                              String email,
                              String password,
                              LocalDate dob) {
        Student student = getStudentById(id);
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (!Objects.equals(student.getSecondName(), secondName)) {
            student.setSecondName(secondName);
        }
        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(student.getLastName(), lastName)) {
            student.setLastName(lastName);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            student.setEmail(email);
        }
        if (password != null &&
                password.length() > 0 &&
                !Objects.equals(student.getPassword(), password)) {
            student.setPassword(password);
        }
        if (dob != null &&
                !Objects.equals(student.getDob(), dob)) {
            student.setDob(dob);
        }
        studentRepository.save(student);
    }
}
