package com.example.osk.user;

import com.example.osk.service.CourseService;
import com.example.osk.user.User;
import com.example.osk.user.UserRepository;
import com.example.osk.user.UserRequest;
import com.example.osk.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final CourseService courseService;

    @Override
    public List<UserRequest> getStudents() {
        List<User> users = userRepository.findAll();
        List<UserRequest> userRequests = new ArrayList<>();
        users.forEach(user -> userRequests.add(new UserRequest(
                user.getId(),
                user.getName(),
                user.getSecondName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getDob(),
                user.getAge()
        )));
        return userRequests;
    }

    @Override
    public UserRequest getUser(Long id) {
        User user = getUserById(id);
        return new UserRequest(
                user.getId(),
                user.getName(),
                user.getSecondName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getDob(),
                user.getAge()
        );
    }

    @Override
    public UserRequest getUser(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Faile to find user with email " + email));
        return new UserRequest(user);
    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "User with id " + id + " does not exist"));
    }

    @Override
    public User saveUser(UserRequest userRequest) {
        User user = new User(userRequest);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        courseService.deleteCoursesByStudent(getUserById(id));
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void updateUser(UserRequest userRequest) {

        User user = getUserById(userRequest.getId());

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
        if (userRequest.getEmail() != null &&
                userRequest.getEmail().length() > 0 &&
                !Objects.equals(user.getEmail(), userRequest.getEmail())) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPassword() != null &&
                userRequest.getPassword().length() > 0 &&
                !Objects.equals(user.getPassword(), userRequest.getPassword())) {
            user.setPassword(userRequest.getPassword());
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
