package app.clothing_management.service;

import app.clothing_management.model.Product;
import app.clothing_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //create product
    public Product save(Product product) {
        return productRepository.save(product);
    }

}
