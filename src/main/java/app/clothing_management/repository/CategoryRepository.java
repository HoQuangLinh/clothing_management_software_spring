package app.clothing_management.repository;

import app.clothing_management.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Locale;

public interface CategoryRepository extends MongoRepository<Category,String> {
}
