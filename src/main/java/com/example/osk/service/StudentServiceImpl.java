package com.example.osk.service;

import com.example.osk.model.Student;
import com.example.osk.model.User;
import com.example.osk.repository.StudentRepository;
import com.example.osk.request.StudentRequest;
import com.example.osk.request.UserRequest;
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
    private final UserService userService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
    }

    @Override
    public List<StudentRequest> getStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentRequest> studentRequestList = new ArrayList<>();
        studentList.forEach(student -> {
            User user = student.getUser();
            UserRequest userRequest = new UserRequest(user.getId(),
                    user.getName(),
                    user.getSecondName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getDob(),
                    user.getAge());
            studentRequestList.add(new StudentRequest(student.getId(), userRequest));
        });
        return studentRequestList;
    }

    @Override
    public StudentRequest getStudent(Long id) {
        Student student = getStudentById(id);
        User user = student.getUser();
        UserRequest userRequest = new UserRequest(
                user.getId(),
                user.getName(),
                user.getSecondName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getDob(),
                user.getAge()
        );
        return new StudentRequest(student.getId(), userRequest);
    }

    private Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Student with id " + id + " does not exist"));
    }

    @Override
    public Student saveStudent(Student student) {
        userService.saveUser(student.getUser());
        return studentRepository.save(student);
    }


    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.deleteById(id);
        userService.deleteUser(student.getUser().getId());
//        courseService.deleteCoursesByStudent(student);

    }
}
