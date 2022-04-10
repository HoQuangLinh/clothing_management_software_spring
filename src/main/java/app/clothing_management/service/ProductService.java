package app.clothing_management.service;

import app.clothing_management.model.Product;
import app.clothing_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product getProductById(String id){return productRepository.findById(id).get();}
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //create product
    public Product save(Product product) {
        return productRepository.save(product);
    }

    //update product
    public ResponseEntity update(Product product, String id){
         var productData=productRepository.findById(id);
         if(!productData.isPresent()){
             return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sản phẩm này");
         }
         var _product=productData.get();
         _product.setName(product.getName());
         _product.setImageUrl(product.getImageUrl());
         _product.setCategoryId(product.getCategoryId());
         _product.setCostPrice(product.getCostPrice());
         _product.setDiscount(product.getDiscount());
         _product.setOriginPrice(product.getOriginPrice());
         _product.setSalePrice(product.getSalePrice());
         var updatedProduct=productRepository.save(_product);
         return ResponseEntity.ok().body(updatedProduct);
    }

}
