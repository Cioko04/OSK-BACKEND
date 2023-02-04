package com.example.osk.controller;

import com.example.osk.dto.ResponseDTO;
import com.example.osk.dto.UserDTO;
import com.example.osk.session.InMemorySessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public InMemorySessionRegistry sessionRegistry;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user){
     manager.authenticate(
             new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
     );

        final String sessionId = sessionRegistry.registerSession(user.getEmail());
        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);

        return ResponseEntity.ok(response);
    }

}
