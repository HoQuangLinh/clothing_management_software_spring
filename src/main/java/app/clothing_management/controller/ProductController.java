package app.clothing_management.controller;

import app.clothing_management.model.Product;
import app.clothing_management.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
public class ProductController {
    // get All Product
    @Autowired
    ProductService productService;
    @GetMapping("/api/products")
    public List<Product> getAllProducts(){
        return  productService.getAllProducts();
    }

    //create a Product
    /*
     *
     *
     */
    @PostMapping(value = "/api/products/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product createProduct(@RequestPart Product product, @RequestPart MultipartFile image){

        return productService.save(product);
    }



}
