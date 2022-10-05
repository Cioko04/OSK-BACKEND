package com.example.osk.controller;

import com.example.osk.model.Category;
import com.example.osk.model.CategoryType;
import com.example.osk.request.CategoryRequest;
import com.example.osk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryRequest>> getCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<List<CategoryRequest>> getCategoriesWithCourses() {
        return new ResponseEntity<>(categoryService.getCategoriesWithCourses(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryRequest> getCategory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(categoryService.getCategoryWithCourses(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addNewCategory(@Valid @RequestBody Category category){
        categoryService.saveCategory(category);
        return new ResponseEntity<>("Category added!", HttpStatus.CREATED);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted! ", HttpStatus.OK);
    }

    @PutMapping( "{categoryId}")
    public ResponseEntity<String> updateStudent(
            @PathVariable("categoryId") Long categoryId,
            @RequestParam(required = false) CategoryType categoryType,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) Integer time) {
        categoryService.updateCategory(
                categoryId,
                categoryType,
                price,
                time);

        return new ResponseEntity<>("Category updated! ", HttpStatus.OK);
    }
}
