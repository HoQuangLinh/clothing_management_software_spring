package app.clothing_management.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("customers")
public class Customer {
    @Id
    private String id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String gender;
    private Integer point;
    private List<String> listOrders = new ArrayList<>();
    private Integer totalPrice = 0;

    public void setListOrders(String orderId){listOrders.add(orderId);}
}
