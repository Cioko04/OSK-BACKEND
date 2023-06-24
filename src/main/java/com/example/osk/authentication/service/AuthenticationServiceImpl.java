package com.example.osk.authentication.service;

import com.example.osk.authentication.AuthenticationRequest;
import com.example.osk.authentication.RegisterRequest;
import com.example.osk.configuration.service.JwtService;
import com.example.osk.token.Token;
import com.example.osk.token.TokenType;
import com.example.osk.token.repository.TokenRepository;
import com.example.osk.user.User;
import com.example.osk.user.UserRequest;
import com.example.osk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        UserRequest userRequest = new UserRequest(request);
        userService.saveUser(userRequest);
    }

    @Override
    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userService.findUserByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        deleteUserTokens(user);
        saveUserToken(user, jwtToken);
        return jwtToken;
    }

    private void deleteUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        tokenRepository.deleteAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .build();
        tokenRepository.save(token);
    }
}
