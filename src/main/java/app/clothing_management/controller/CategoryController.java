package app.clothing_management.controller;

import app.clothing_management.model.Category;
import app.clothing_management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<Category> getAllCategories(){
        return  categoryService.getAllCategory();

    }
    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category){
       return categoryService.createCategory(category);
   }
}
