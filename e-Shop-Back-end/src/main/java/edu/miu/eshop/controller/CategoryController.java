package edu.miu.eshop.controller;

import edu.miu.eshop.model.Category;
import edu.miu.eshop.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = {"api", "api/categories"})
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = {"/main"})
    public List<Category> getAllMain() {
        return categoryService.getAllMain();
    }

    @GetMapping(value = {"/subById/{id}"})
    public List<Category> getSubById(@PathVariable long id) {
        return categoryService.getSubById(id);
    }
}
