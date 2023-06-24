package com.example.osk.authentication.service;

import com.example.osk.authentication.AuthenticationRequest;
import com.example.osk.authentication.RegisterRequest;

import javax.transaction.Transactional;

public interface AuthenticationService {

    void register(RegisterRequest request);

    @Transactional
    String authenticate(AuthenticationRequest request);
}
