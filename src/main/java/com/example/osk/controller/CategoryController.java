package com.example.osk.controller;

import com.example.osk.model.Category;
import com.example.osk.model.CategoryType;
import com.example.osk.dto.CategoryRequest;
import com.example.osk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/categories")
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

    @GetMapping("{id}")
    public ResponseEntity<CategoryRequest> getCategory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addNewCategory(@Valid @RequestBody Category category) {
        categoryService.saveCategory(category);
        return new ResponseEntity<>("Category added!", HttpStatus.CREATED);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted! ", HttpStatus.OK);
    }

    @PutMapping(path = "delete")
    public ResponseEntity<String> deleteCategoryFormInstructor(@RequestParam Long instructorId,
                                                              @RequestParam Long categoryId) {
        categoryService.deleteCategoryFromInstructor(instructorId, categoryId);
        return new ResponseEntity<>("Category deleted! ", HttpStatus.OK);
    }

    @PutMapping(path = "add")
    public ResponseEntity<String> updateCategoryForInstructor(@RequestParam Long instructorId,
                                                              @RequestParam Long categoryId) {
        categoryService.addCategoryForInstructor(instructorId, categoryId);
        return new ResponseEntity<>("Category updated! ", HttpStatus.OK);
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<String> updateStudent(@PathVariable("categoryId") Long categoryId,
                                                @RequestParam(required = false) CategoryType categoryType,
                                                @RequestParam(required = false) Integer price,
                                                @RequestParam(required = false) Integer time) {

        categoryService.updateCategory(categoryId, categoryType, price, time);

        return new ResponseEntity<>("Category updated! ", HttpStatus.OK);
    }
}
