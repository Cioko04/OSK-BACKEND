package com.example.osk.commonUser.service;

import com.example.osk.commonUser.CommonUser;
import com.example.osk.commonUser.CommonUserRequest;
import com.example.osk.commonUser.repository.CommonUserRepository;
import com.example.osk.user.Role;
import com.example.osk.user.User;
import com.example.osk.user.UserRequest;
import com.example.osk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommonUserServiceImpl implements CommonUserService {
    private final CommonUserRepository commonUserRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<CommonUserRequest> getStudents() {
        List<CommonUser> users = commonUserRepository.findAll();
        List<CommonUserRequest> userRequests = new ArrayList<>();
        users.forEach(user -> userRequests.add(new CommonUserRequest(
                user.getId(),
                user.getName(),
                user.getSecondName(),
                user.getLastName(),
                user.getDob(),
                user.getAge()
        )));
        return userRequests;
    }

    @Override
    public CommonUserRequest getCommonUser(String email) {
        CommonUser commonUser = commonUserRepository.findCommonUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Faile to find commonUser with email " + email));
        return new CommonUserRequest(commonUser);
    }

    private CommonUser getUserById(Long id) {
        return commonUserRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Common User with id " + id + " does not exist"));
    }

    @Override
    public void saveCommonUser(CommonUserRequest commonUserRequest) {
        CommonUser commonUser = new CommonUser(commonUserRequest);
        User user = userService.saveUser(commonUserRequest);
        commonUser.setUser(user);
        commonUserRepository.save(commonUser);

    }

    @Override
    public void deleteCommonUser(Long id) {
        commonUserRepository.deleteById(id);
    }

    @Override
    public void updateCommonUser(CommonUserRequest userRequest) {
        userService.updateUser(userRequest);
        CommonUser commonUser = getUserById(userRequest.getId());

        if (userRequest.getName() != null &&
                userRequest.getName().length() > 0 &&
                !Objects.equals(commonUser.getName(), userRequest.getName())) {
            commonUser.setName(userRequest.getName());
        }
        if (userRequest.getSecondName() != null &&
                userRequest.getSecondName().length() > 0 &&
                !Objects.equals(commonUser.getSecondName(), userRequest.getSecondName())) {
            commonUser.setSecondName(userRequest.getSecondName());
        }
        if (userRequest.getLastName() != null &&
                userRequest.getLastName().length() > 0 &&
                !Objects.equals(commonUser.getLastName(), userRequest.getLastName())) {
            commonUser.setLastName(userRequest.getLastName());
        }
        if (userRequest.getDob() != null &&
                !Objects.equals(commonUser.getDob(), userRequest.getDob())) {
            commonUser.setDob(userRequest.getDob());
        }

        commonUserRepository.save(commonUser);
    }
}
