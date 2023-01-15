package com.example.osk.service;

import com.example.osk.model.User;
import com.example.osk.repository.UserRepository;
import com.example.osk.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CourseService courseService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CourseService courseService) {
        this.userRepository = userRepository;
        this.courseService = courseService;
    }

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

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "User with id " + id + " does not exist"));
    }

    @Override
    public User saveUser(User user) {
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
    public void updateUser(Long id,
                           String name,
                           String secondName,
                           String lastName,
                           String email,
                           String password,
                           LocalDate dob) {

        User user = getUserById(id);

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }
        if (secondName != null &&
                secondName.length() > 0 &&
                !Objects.equals(user.getSecondName(), secondName)) {
            user.setSecondName(secondName);
        }
        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(user.getLastName(), lastName)) {
            user.setLastName(lastName);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(user.getEmail(), email)) {
            user.setEmail(email);
        }
        if (password != null &&
                password.length() > 0 &&
                !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }
        if (dob != null &&
                !Objects.equals(user.getDob(), dob)) {
            user.setDob(dob);
        }

        userRepository.save(user);
    }
}
