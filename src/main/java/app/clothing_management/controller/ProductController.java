package app.clothing_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    // get All Product
    @GetMapping("/api/products")
    public String getAllProducts(){
        return  "List all products";
    }


    // List all product

}
