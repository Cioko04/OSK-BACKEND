package com.example.osk.student;

import com.example.osk.model.Student;
import com.example.osk.repository.StudentRepository;
import com.example.osk.service.StudentServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

//    @Mock
//    private StudentRepository studentRepository;
//    private StudentServiceImpl underTest;
//
////    @BeforeEach
////    void setUp() {
////        underTest = new StudentService(studentRepository, courseRepository);
////    }
//
//    @Test
//    void canGetAllStudents() {
//        // when
//        underTest.getAllStudents();
//        // then
//        verify(studentRepository).findAll();
//    }
//
//    @Test
//    void canAddStudent() {
//        // given
//        Student student = new Student(
//                "Jamila",
//                "",
//                "Jackowiak",
//                "jamila@gmail.com",
//                "xyz",
//                LocalDate.of(1989, Month.DECEMBER, 5)
//        );
//        // when
//        underTest.addStudent(student);
//
//        //then
//        ArgumentCaptor<Student> studentArgumentCaptor =
//                ArgumentCaptor.forClass(Student.class);
//
//        verify(studentRepository)
//                .save(studentArgumentCaptor.capture());
//
//        Student capturedStudent = studentArgumentCaptor.getValue();
//        assertThat(capturedStudent).isEqualTo(student);
//    }
//
//    @Test
//    void willThrowWhenEmailIsTaken() {
//        // given
//        Student student = new Student(
//                "Jamila",
//                "",
//                "Jackowiak",
//                "jamila@gmail.com",
//                "xyz",
//                LocalDate.of(1989, Month.DECEMBER, 5)
//        );
//
//        given(studentRepository.findStudentByEmail(student.getEmail()))
//                .willReturn(Optional.of(student));
//        // when
//
//        //then
//
//        assertThatThrownBy(() -> underTest.addStudent(student))
//                .isInstanceOf(IllegalStateException.class)
//                .hasMessageContaining("Email is already taken!");
//
//        verify(studentRepository, never()).save(any());
//
//    }
//
//    @Test
//    @Disabled
//    void deleteStudent() {
//    }
//
//    @Test
//    @Disabled
//    void updateStudent() {
//    }
}