package com.example.osk.student;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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