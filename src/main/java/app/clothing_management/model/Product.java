package app.clothing_management.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document("products")
public class Product {
    @Id
    private  String id;
    private  String name;
    private  Double originPrice;
    private  Double costPrice;
    private  Double discount;
    private  Double salePrice;
    private  String imageUrl;
    private  String qrCodeUrl;


}
