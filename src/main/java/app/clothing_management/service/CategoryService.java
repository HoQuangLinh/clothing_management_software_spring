package app.clothing_management.service;

import app.clothing_management.model.Category;
import app.clothing_management.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return  categoryRepository.findAll();
    }
    public  Category createCategory(Category category){
        return categoryRepository.save(category);
    }
}
