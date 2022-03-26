package app.clothing_management.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("products")
public class Product {
    @Id
    private  String id;
    private  String productName;
    private Integer price;
}
