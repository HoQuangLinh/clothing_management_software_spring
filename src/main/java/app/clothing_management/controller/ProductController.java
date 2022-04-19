package app.clothing_management.controller;

import app.clothing_management.config.CloudinaryConfig;
import app.clothing_management.config.QRCodeGenerator;
import app.clothing_management.model.Product;
import app.clothing_management.service.ProductService;

import com.cloudinary.utils.ObjectUtils;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public List<Product> getAllProducts(){
        return  productService.getAllProducts();
    }

    //create a Product
    /* input fields:
        + categoryId
        + name
        + costPrice
        + discount
        + salePrice
        + originPrice
        + image

      * return Object Product

     *
     */
    @PostMapping(value = "/api/products/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product createProduct(Product product, @RequestParam(required = false) MultipartFile image )  {

        UploadImage(product, image);
        var newProduct= productService.save(product);
        String productId= newProduct.getId();
        //get Qrcode from productId
        byte[] imageQr=new byte[0];
        try {
            imageQr= QRCodeGenerator.getQRCodeImage(productId,250,250);
            Map uploadResult= null;
            uploadResult = cloudinaryConfig.
                    upload(imageQr, ObjectUtils.asMap("resource_type", "auto", "overwrite", true));
            newProduct.setQrCodeUrl(uploadResult.get("url").toString());
            return productService.save(newProduct);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private void UploadImage(Product product, @RequestParam(required = false) MultipartFile image) {
        if(image!=null){
            Map uploadResult= null;
            try {
                uploadResult = cloudinaryConfig.
                        upload(image.getBytes(), ObjectUtils.asMap("resource_type", "auto", "overwrite", true));
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setImageUrl(uploadResult.get("url").toString());

        }
    }

    //Update product
    @PutMapping(value = "/api/products/update/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity updateProduct(Product product, @PathVariable("id") String id,@RequestParam(required = false) MultipartFile image){
        UploadImage(product, image);
        return productService.update(product,id);
    }
    //Filter products by Categories
    @GetMapping("/api/products/productsByCategoryId/{categoryId}")
    public List<Product> getProductsByCategoryId(@PathVariable String categoryId){
        //return categoryId;
        return productService.getProductByCategoryId(categoryId);
    }

    //Test Qr code
    @GetMapping("/api/products/qr")
    public String getQr(){
        String medium="https://rahul26021999.medium.com/";
        byte[] image=new byte[0];
        try {
            image= QRCodeGenerator.getQRCodeImage(medium,200,200);
            Map uploadResult= null;
            uploadResult = cloudinaryConfig.
                    upload(image, ObjectUtils.asMap("resource_type", "auto", "overwrite", true));
            return uploadResult.get("url").toString();
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
