package com.example.osk.instructor.service;

import com.example.osk.instructor.Instructor;
import com.example.osk.instructor.InstructorRequest;
import com.example.osk.instructor.repository.InstructorRepository;
import com.example.osk.school.School;
import com.example.osk.school.service.SchoolService;
import com.example.osk.user.Role;
import com.example.osk.user.User;
import com.example.osk.user.UserRequest;
import com.example.osk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final SchoolService schoolService;
    private final UserService userService;

    @Override
    public List<InstructorRequest> getInstructorsBySchoolId(Long id) {
        Set<Instructor> instructors = instructorRepository.findAllBySchoolId(id);
        return instructors.stream().map(InstructorRequest::new).collect(Collectors.toList());
    }

    @Override
    public Instructor saveInstructor(InstructorRequest instructorRequest) {
        School school = schoolService.getSchoolById(instructorRequest.getSchoolId());
        UserRequest userRequest = instructorRequest.getUserRequest();
        userRequest.setRole(Role.INSTRUCTOR);
        User user = userService.saveUser(userRequest);
        Instructor instructor = Instructor.builder()
                .school(school)
                .user(user)
                .build();
        return instructorRepository.save(instructor);
    }

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepository.deleteById(id);
    }

}
