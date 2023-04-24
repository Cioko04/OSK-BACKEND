package com.example.osk.user.service;

import com.example.osk.user.User;
import com.example.osk.user.UserRequest;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    User saveUser(UserRequest userRequest);

    boolean existsByEmail(String email);

    @Transactional
    void updateUser(UserRequest userRequest);
}
