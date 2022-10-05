package com.example.osk.controller;

import com.example.osk.model.Student;
import com.example.osk.request.StudentRequest;
import com.example.osk.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentRequest>> getStudents() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentRequest> getStudent(@PathVariable("id") Long id){
        return new ResponseEntity<>(studentService.getStudentWithCourses(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> registerNewStudent(@Valid @RequestBody Student student){
        studentService.saveStudent(student);
        return new ResponseEntity<>("Student saved! ", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student deleted! ", HttpStatus.OK);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<String> updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String secondName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) LocalDate dob) {
        studentService.updateStudent(
                studentId,
                name,
                secondName,
                lastName,
                email,
                password,
                dob);

        return new ResponseEntity<>("Student updated! ", HttpStatus.OK);
    }
}
