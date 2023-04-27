package com.example.osk.user.service;

import com.example.osk.user.User;
import com.example.osk.user.UserRequest;

import javax.transaction.Transactional;

public interface UserService {

    UserRequest getUser(String email);

    User saveUser(UserRequest userRequest);

    boolean existsByEmail(String email);

    @Transactional
    void updateUser(UserRequest userRequest);
}
