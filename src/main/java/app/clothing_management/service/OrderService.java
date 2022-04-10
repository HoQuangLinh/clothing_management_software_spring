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
import java.util.List;

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
}
