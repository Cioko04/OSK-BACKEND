package com.example.osk.category.repository;

import com.example.osk.category.Category;
import com.example.osk.category.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryType(CategoryType categoryType);
}
