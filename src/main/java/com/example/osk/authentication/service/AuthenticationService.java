package com.example.osk.authentication.service;

import com.example.osk.authentication.AuthenticationRequest;
import com.example.osk.authentication.AuthenticationResponse;
import com.example.osk.authentication.RegisterRequest;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    void register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    boolean checkTokenValidity(String request, String email);
}
