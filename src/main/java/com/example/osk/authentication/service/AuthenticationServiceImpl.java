package com.example.osk.authentication.service;

import com.example.osk.authentication.AuthenticationRequest;
import com.example.osk.authentication.AuthenticationResponse;
import com.example.osk.authentication.RegisterRequest;
import com.example.osk.configuration.service.JwtService;
import com.example.osk.token.Token;
import com.example.osk.token.TokenType;
import com.example.osk.token.repository.TokenRepository;
import com.example.osk.user.Role;
import com.example.osk.user.User;
import com.example.osk.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public void register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Faile to find user with email " + request.getEmail()));
        String jwtToken = jwtService.generateToken(user);
        deleteUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public boolean checkTokenValidity(String token, String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        token = token.substring(7);
        boolean isTokenValid = tokenRepository.findByToken(token).isPresent();
        return jwtService.isTokenValid(token, userDetails) && isTokenValid;
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
