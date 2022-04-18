package app.clothing_management.repository;

import app.clothing_management.model.Product;
import app.clothing_management.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
    @Query("{categoryId : ?0}")
    List<Product> getProductsByCategoryId(String categoryId);
}
