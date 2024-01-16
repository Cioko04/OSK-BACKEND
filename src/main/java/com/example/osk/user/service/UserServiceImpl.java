package com.example.osk.user.service;

import com.example.osk.common.FieldUpdatable;
import com.example.osk.school.SchoolRequest;
import com.example.osk.user.User;
import com.example.osk.user.UserRequest;
import com.example.osk.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService, FieldUpdatable {
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
        UserRequest userRequest = new UserRequest(user);
        if (user.getSchool() != null) {
            SchoolRequest schoolRequest = new SchoolRequest(user.getSchool());
            userRequest.setSchoolRequest(schoolRequest);
        }
        return userRequest;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUserByIdIfExists(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id " + id + " does not exist"));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Faile to find user with email " + email));
    }

    @Override
    public List<UserRequest> getUsersWithSchool() {
        Set<User> usersWithSchool = userRepository.findUsersWithSchool();
        return usersWithSchool.stream().map(UserRequest::new).toList();
    }

    @Override
    public User saveUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .secondName(userRequest.getSecondName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .dob(userRequest.getDob())
                .role(userRequest.getRole())
                .build();
        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void updateUser(UserRequest userRequest) {
        User user = getUserById(userRequest.getId());

        updateFieldIfChanged(userRequest.getEmail(), user.getEmail(), user::setEmail);
        updateFieldIfChanged(userRequest.getPassword(), user.getPassword(),
                updatedPassword -> user.setPassword(passwordEncoder.encode(updatedPassword)));
        updateFieldIfChanged(userRequest.getName(), user.getName(), user::setName);
        updateFieldIfChanged(userRequest.getSecondName(), user.getSecondName(), user::setSecondName);
        updateFieldIfChanged(userRequest.getLastName(), user.getLastName(), user::setLastName);
        updateFieldIfChanged(userRequest.getDob(), user.getDob(), user::setDob);

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Faile to find user with email " + email));
    }
}
