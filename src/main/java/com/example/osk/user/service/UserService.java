package com.example.osk.user.service;

import com.example.osk.user.User;
import com.example.osk.user.UserRequest;

import java.util.List;

public interface UserService {

    UserRequest getUser(String email);

    User findUserByEmail(String email);

    List<UserRequest> getUsersWithSchool();

    User saveUser(User user);

    boolean existsByEmail(String email);

    void updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}
