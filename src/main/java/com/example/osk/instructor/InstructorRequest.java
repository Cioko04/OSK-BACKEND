package com.example.osk.instructor;

import com.example.osk.user.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstructorRequest {
    private Long id;
    private Long schoolId;
    private UserRequest userRequest;
    private Set<String> categories;

    public InstructorRequest(Instructor instructor){
        this.id = instructor.getId();
        this.schoolId = instructor.getSchool().getId();
        this.userRequest = new UserRequest(instructor.getUser());
        this.categories = instructor.getCategories().stream()
                .map(category -> category.getCategoryType().getValue())
                .collect(Collectors.toSet());
    }
}
