package app.clothing_management.repository;

import app.clothing_management.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ProductRepository extends MongoRepository<Product,String> {
    Optional<Product> findProductById(String id);
}
