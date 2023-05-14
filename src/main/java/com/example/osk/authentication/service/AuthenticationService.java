package com.example.osk.authentication.service;

import com.example.osk.authentication.AuthenticationRequest;
import com.example.osk.authentication.AuthenticationResponse;
import com.example.osk.authentication.RegisterRequest;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

public interface AuthenticationService {

    void register(RegisterRequest request);

    @Transactional
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
