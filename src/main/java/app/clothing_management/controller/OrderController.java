package app.clothing_management.controller;

import app.clothing_management.model.*;
import app.clothing_management.service.CustomerService;
import app.clothing_management.service.OrderService;
import app.clothing_management.service.ProductService;
import app.clothing_management.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(path = "/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    CustomerService customerService;
    //Lấy toàn bộ order
    @GetMapping("/")
    public List<Order> getOrders(){return orderService.getOrders();}
    //Xác nhận order
    // nếu mà xãy ra lỗi thì có thể do chỗ orderDetails truyền là product
    //mà cái trong hàm này nhận là productId
    @PostMapping(value = "/create")
    public ResponseEntity<?> confirmOrder(@RequestBody ObjectNode objectNode){
        //Lấy dữ liệu từ request
        String userId = objectNode.get("user").asText();
        String customerId = objectNode.get("customer").asText();
        Integer subTotal = objectNode.get("subTotal").asInt();
        Integer discount = objectNode.get("discount").asInt();
        Integer orderTotal = objectNode.get("orderTotal").asInt();
        Integer point = objectNode.get("point").asInt();
        String orderDetailString = objectNode.get("orderDetails").toString();
        //Convert orderDetail
        List<OrderDetail> orderDetails = convertStringToOrderDetail(orderDetailString);
        //Tạo order
        User user = userService.findById(userId);
        Customer customer = customerService.getCustomerById(customerId);
        Order order = new Order();
        order.setUser(user);
        order.setCustomer(customer);
        order.setSubTotal(subTotal);
        order.setDiscount(discount);
        order.setOrderTotal(orderTotal);

        if(customer.getName().equals("Khách lẻ")){
            point = 0;
        }
       try {
           Order newOrder = orderService.confirmOrder(order,orderDetails,point);
           return new ResponseEntity<>(newOrder,HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>("Tạo order thất bại",HttpStatus.BAD_REQUEST);
       }
    }
    public List<OrderDetail> convertStringToOrderDetail(String s){
        System.out.println(s);
        List<OrderDetail> orderDetails = new ArrayList<>();
        String ss = s.substring(1, s.length() - 1);
        String[] arr = ss.split(",");
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                OrderDetail orderDetail = new OrderDetail();
                String productId = arr[i].substring(14, arr[i].length() - 1);
                System.out.println(productId);
                Product product = productService.getProductById(productId);
                orderDetail.setProduct(product);
                String quantity = arr[i+1].substring(12, arr[i+1].length() - 2);
                orderDetail.setQuantity(Integer.valueOf(quantity));
                orderDetails.add(orderDetail);
            }
        }
        return orderDetails;
    }
    //Lọc order theo khoảng ngày
    @GetMapping("/filterDate")
    public List<Order> getOrdersByRangeOfDate(@RequestParam("begin") Date begin,
                                              @RequestParam("end") Date end){
        return orderService.getOrdersByDate(begin, end);
    }
    //Lọc order theo tên nhân viên bán
    @GetMapping("/filterStaffName")
    public List<Order> getOrdersByStaffName(@RequestParam("key") String key){
        return orderService.getOrdersByStaffName(key);
    }
    //Lọc order theo tên khách hàng
    @GetMapping("/filterCustomerName")
    public List<Order> getOrdersByCustomerName(@RequestParam("key") String key){
        return orderService.getOrdersByCustomerName(key);
    }
    //Lọc order theo id của order
    @GetMapping("/filterId")
    public List<Order> getOrdersById(@RequestParam("key") String key){
        return orderService.getOrdersById(key);
    }
    //Lọc order theo trạng thái
    @GetMapping("/filterStatus")
    public List<Order> getOrdersByStatus(@RequestParam("status") String status){
        return orderService.getOrderByStatus(status);
    }
}
