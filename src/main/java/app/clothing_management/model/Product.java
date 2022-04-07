package app.clothing_management.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data

//@Document("products")
public class Product {
   // @Id
   // public   String id;
     public     String name;
//    private  Double originPrice;
//    private  Double costPrice;
//    private  Double discount;
//    private  Double salePrice;
//    private  String imageUrl;
//    private  String qrCodeUrl;
    // Referent to Category
    //private String categoryId;

}
