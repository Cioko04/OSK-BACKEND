package com.example.osk.service;

import com.example.osk.model.User;
import com.example.osk.request.UserRequest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<UserRequest> getStudents();

    UserRequest getUser(Long id);

    User saveUser(User user);

    void deleteUser(Long id);

    @Transactional
    void updateUser(Long id,
                    String name,
                    String secondName,
                    String lastName,
                    String email,
                    String password,
                    LocalDate dob);
}
