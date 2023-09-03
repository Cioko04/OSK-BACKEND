package com.example.osk.category.service;

import com.example.osk.category.Category;
import com.example.osk.category.CategoryType;

import java.util.Set;

public interface CategoryService {
    Category getCategory(CategoryType categoryType);

    Set<Category> getUniqueValuesFromSecondList(Set<String> firstCategories, Set<String> secondCategories);

    Set<Category> getCategoriesFromStringList(Set<String> categories);
}
