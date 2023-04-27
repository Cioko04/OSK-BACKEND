package com.example.osk.user.service;

import com.example.osk.user.Role;
import com.example.osk.user.User;
import com.example.osk.user.UserRequest;
import com.example.osk.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "User with id " + id + " does not exist"));
    }

    @Override
    public UserRequest getUser(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Failed to find user with email " + email));
        return new UserRequest(user);
    }

    @Override
    public User saveUser(UserRequest userRequest) {
        User user = User.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .role(userRequest.getRole())
                .name(userRequest.getName())
                .secondName(userRequest.getSecondName())
                .lastName(userRequest.getLastName())
                .dob(userRequest.getDob())
                .build();
        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void updateUser(UserRequest userRequest) {

        User user = getUserById(userRequest.getId());

        if (userRequest.getEmail() != null &&
                userRequest.getEmail().length() > 0 &&
                !Objects.equals(user.getEmail(), userRequest.getEmail())) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPassword() != null &&
                userRequest.getPassword().length() > 0 &&
                !Objects.equals(user.getPassword(), userRequest.getPassword())) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        if (userRequest.getName() != null &&
                userRequest.getName().length() > 0 &&
                !Objects.equals(user.getName(), userRequest.getName())) {
            user.setName(userRequest.getName());
        }
        if (userRequest.getSecondName() != null &&
                userRequest.getSecondName().length() > 0 &&
                !Objects.equals(user.getSecondName(), userRequest.getSecondName())) {
            user.setSecondName(userRequest.getSecondName());
        }
        if (userRequest.getLastName() != null &&
                userRequest.getLastName().length() > 0 &&
                !Objects.equals(user.getLastName(), userRequest.getLastName())) {
            user.setLastName(userRequest.getLastName());
        }
        if (userRequest.getDob() != null &&
                !Objects.equals(user.getDob(), userRequest.getDob())) {
            user.setDob(userRequest.getDob());
        }
        userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Faile to find user with email " + email));
    }
}
