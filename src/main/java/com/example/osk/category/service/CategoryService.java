package com.example.osk.category.service;

import com.example.osk.category.Category;
import com.example.osk.category.CategoryType;

public interface CategoryService {
    Category getCategory(CategoryType categoryType);
}
