package app.clothing_management.controller;

import app.clothing_management.config.CloudinaryConfig;
import app.clothing_management.model.Product;
import app.clothing_management.service.ProductService;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class ProductController {
    // get All Product
    @Autowired
    ProductService productService;
    @Autowired
    CloudinaryConfig cloudinaryConfig;
    @GetMapping("/api/products")
    public String getAllProducts(){

        return "Hekki";
        //return  productService.getAllProducts();
    }

    //create a Product
    /*
     *
     *
     */
    @PostMapping(value = "/api/products/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createProduct(Product product, @RequestParam(required = false) MultipartFile file ) throws IOException {
        //return productService.save(product);
        if(file!=null){
            Map uploadResult=cloudinaryConfig.
                    upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
          String x=  uploadResult.get("url").toString();
          return x;
        }
        return "No file";


    }



}
