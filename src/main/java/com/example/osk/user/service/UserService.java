package com.example.osk.user.service;

import com.example.osk.user.User;
import com.example.osk.user.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserRequest getUser(String email);

    User findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    List<UserRequest> getUsersWithSchool();

    User saveUser(UserRequest userRequest);

    boolean existsByEmail(String email);

    void updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}
