package com.example.osk;

import com.example.osk.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OskApplicationTests {
    @Autowired
    StudentRepository studentRepository;

    @Test
    void contextLoads() {
    }


}
