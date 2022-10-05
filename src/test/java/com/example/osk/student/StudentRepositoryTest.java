package com.example.osk.student;

import com.example.osk.model.Student;
import com.example.osk.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class StudentRepositoryTest {
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @AfterEach
//    void tearDown() {
//        studentRepository.deleteAll();
//    }
//
////    @Test
////    void itShouldFindStudentByEmail() {
////        //given
////        String email = "jamila@gmail.com";
////        Student student = new Student(
////                "Jamila",
////                "",
////                "Jackowiak",
////                "jamila@gmail.com",
////                "xyz",
////                LocalDate.of(1989, Month.DECEMBER, 5)
////        );
////        studentRepository.save(student);
////        //when
////        boolean exists = studentRepository.findStudentByEmail(email).isPresent();
////        //then
////        assertThat(exists).isTrue();
////    }
//
//    @Test
//    void itShouldNotFindStudentByEmail() {
//        //given
//        String email = "jamilaa@gmail.com";
//        //when
//        boolean exists = studentRepository.findStudentByEmail(email).isPresent();
//        //then
//        assertThat(exists).isFalse();
//    }

}