package com.example.osk.category.service;

import com.example.osk.category.Category;
import com.example.osk.category.CategoryType;
import com.example.osk.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public Category getCategory(CategoryType categoryType) {
        return categoryRepository.findByCategoryType(categoryType).orElseThrow(() -> new IllegalStateException(
                "Category with type " + categoryType + " does not exist"));
    }

    @Override
    public Set<Category> getUniqueValuesFromSecondList(Set<String> firstCategories, Set<String> secondCategories) {
        return secondCategories.stream()
                .filter(categoryType -> !firstCategories.contains(categoryType))
                .map(categoryType -> getCategory(CategoryType.getCategoryTypeFromString(categoryType)))
                .collect(Collectors.toSet());
    }
}
