package com.example.osk;

import com.example.osk.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OskApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
    }


}
