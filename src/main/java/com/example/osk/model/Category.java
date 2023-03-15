package com.example.osk.model;


import com.example.osk.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "categories",
        uniqueConstraints = @UniqueConstraint(
                name = "category_unique",
                columnNames = "category_type"
        )
)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "category_type")
    private CategoryType categoryType;
    @NotNull
    private Integer price;
    @NotNull
    private Integer time;
    @OneToMany(mappedBy = "category")
    List<Course> courses = new ArrayList<>();
    @ManyToMany(mappedBy = "categoryList")
    private List<User> instructorList = new ArrayList<>();


}
