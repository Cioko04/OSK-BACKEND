package com.example.osk.category;

import com.example.osk.school.School;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(
        name = "categories"
)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int duration;
    @Enumerated
    private CategoryType categoryType;

    @ManyToMany(mappedBy = "categories")
    private Set<School> schools = new HashSet<>();
}
