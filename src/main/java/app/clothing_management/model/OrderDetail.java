package app.clothing_management.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("order_details")
public class OrderDetail {
    @Id
    private String id;
    private Product product;
    private Integer quantity;
}
