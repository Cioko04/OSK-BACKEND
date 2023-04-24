package com.example.osk.commonUser.service;

import com.example.osk.commonUser.CommonUser;
import com.example.osk.commonUser.CommonUserRequest;
import com.example.osk.user.UserRequest;

import javax.transaction.Transactional;
import java.util.List;

public interface CommonUserService {
    List<CommonUserRequest> getStudents();

    CommonUserRequest getCommonUser(String email);

    void saveCommonUser(CommonUserRequest commonUserRequest);

    void deleteCommonUser(Long id);

    @Transactional
    void updateCommonUser(CommonUserRequest commonUserRequest);
}
