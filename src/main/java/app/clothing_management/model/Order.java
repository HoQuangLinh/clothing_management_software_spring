package app.clothing_management.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document("orders")
public class Order {
    @Id
    private String id;
    private User user;
    private Customer customer;
    private Date dateOrder = new Date();
    private Integer subTotal;
    private Integer discount;
    private Integer orderTotal;
    private String qrCodeUrl;
    private List<OrderDetail> orderDetails = new ArrayList<>();
    private String status = "Chưa thanh toán";
    private Integer totalReturnPrice = 0;
}
