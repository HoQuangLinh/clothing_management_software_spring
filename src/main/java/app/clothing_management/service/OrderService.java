package app.clothing_management.service;

import app.clothing_management.model.Customer;
import app.clothing_management.model.Order;
import app.clothing_management.model.OrderDetail;
import app.clothing_management.repository.CustomerRepository;
import app.clothing_management.repository.OrderDetailRepository;
import app.clothing_management.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    CustomerRepository customerRepository;
    //Tạo order
    public Order confirmOrder(Order order, List<OrderDetail> orderDetail, int point){
        List<OrderDetail> orderDetails = orderDetailRepository.insert(orderDetail);
        order.setOrderDetails(orderDetails);
        order.setStatus("Đã thanh toán");
        Order newOrder = orderRepository.save(order);
        Customer customer = customerRepository.findById(order.getCustomer().getId()).get();
        customer.setListOrders(newOrder.getId());
        customer.setPoint(customer.getPoint()+point);
        customerRepository.save(customer);
        return newOrder;
    }
    //Lấy tất cả order
    public List<Order> getOrders(){
       return orderRepository.findAll();
    }
    //Lọc order theo thời gian
    public List<Order> getOrdersByDate(Date begin, Date end) {
        return orderRepository.getOrdersByRangeOfDate(begin, end);
    }
    //Lọc order theo tên nhân viên bán
    public List<Order> getOrdersByStaffName(String key){
        List<Order> orders  = orderRepository.findAll();
        List<Order> result = new ArrayList<>();
        for (Order o : orders) {
            if(o.getUser().getFullname().toLowerCase(Locale.ROOT).contains(key.toLowerCase(Locale.ROOT))){
                result.add(o);
            }
        }
        return result;
    }
    //Lọc order theo tên khách hàng
    public List<Order> getOrdersByCustomerName(String key){
        List<Order> orders  = orderRepository.findAll();
        List<Order> result = new ArrayList<>();
        for (Order o : orders) {
            if(o.getCustomer().getName().toLowerCase(Locale.ROOT).contains(key.toLowerCase(Locale.ROOT))){
                result.add(o);
            }
        }
        return result;
    }
    //Lọc theo id của Order
    public List<Order> getOrdersById(String key){
        List<Order> orders  = orderRepository.findAll();
        List<Order> result = new ArrayList<>();
        for (Order o : orders) {
            if(o.getId().contains(key.toLowerCase(Locale.ROOT))){
                result.add(o);
            }
        }
        return result;
    }
    //Lọc order theo trạng thái
    public List<Order> getOrderByStatus(String status){
        return orderRepository.findOrderByStatus(status);
    }
}
