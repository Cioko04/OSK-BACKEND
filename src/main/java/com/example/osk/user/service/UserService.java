package com.example.osk.user.service;

import com.example.osk.user.User;
import com.example.osk.user.UserRequest;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    List<UserRequest> getStudents();

    UserRequest getUser(Long id);

    UserRequest getUser(String email);

    User saveUser(UserRequest userRequest);

    void deleteUser(Long id);

    boolean existsByEmail(String email);

    @Transactional
    void updateUser(UserRequest userRequest);
}
