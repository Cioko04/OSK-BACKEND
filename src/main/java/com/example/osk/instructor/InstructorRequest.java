package com.example.osk.instructor;

import com.example.osk.user.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstructorRequest {
    private Long id;
    private Long schoolId;
    private UserRequest userRequest;

    public InstructorRequest(Instructor instructor){
        this.id = instructor.getId();
        this.schoolId = instructor.getSchool().getId();
        this.userRequest = new UserRequest(instructor.getUser());
    }
}
