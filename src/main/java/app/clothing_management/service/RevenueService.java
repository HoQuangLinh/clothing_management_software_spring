package app.clothing_management.service;

import app.clothing_management.model.Order;
import app.clothing_management.model.OrderDetail;
import app.clothing_management.model.Product;
import app.clothing_management.model.ProductSell;
import app.clothing_management.repository.OrderDetailRepository;
import app.clothing_management.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RevenueService {

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    // Lấy tất cả các sản phẩm đã bán và số lượng đã bán, tiền lời, doanh thu.
    public List<ProductSell> getProductsSell(){
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        Map<Product, Integer> sum = orderDetails.stream().collect(
                Collectors.groupingBy(OrderDetail::getProduct, Collectors.summingInt(OrderDetail::getQuantity))
        );
        List<ProductSell> sellProducts = new ArrayList<>();
        Set<Product> set = sum.keySet();
        for (Product p : set){
            ProductSell sellProduct = new ProductSell();
            sellProduct.set_id(p.getId());
            sellProduct.setProductName(p.getName());
            sellProduct.setSellQuantity(sum.get(p));
            sellProduct.setRevenue(sellProduct.getSellQuantity()*p.getCostPrice());
            sellProduct.setProfit(sellProduct.getRevenue()-p.getOriginPrice()* sellProduct.getSellQuantity());
            sellProducts.add(sellProduct);
        }
        return sellProducts;
    }

    // Lọc sản phẩm đã bán theo khảng thời gian
    public List<ProductSell> getProductsSellByDate(Date from, Date to){
        List<Order> orders = orderRepository.getOrderByDateOrderBetween(from, to);
        Map<Product, Integer> sum = new HashMap<>();
        for(Order o : orders){
            for(OrderDetail orderDetail : o.getOrderDetails()){
                sum.put(orderDetail.getProduct(), orderDetail.getQuantity());
            }
        }
        List<ProductSell> sellProducts = new ArrayList<>();
        Set<Product> set = sum.keySet();
        for (Product p : set){
            ProductSell sellProduct = new ProductSell();
            sellProduct.set_id(p.getId());
            sellProduct.setProductName(p.getName());
            sellProduct.setSellQuantity(sum.get(p));
            sellProduct.setRevenue(sellProduct.getSellQuantity()*p.getCostPrice());
            sellProduct.setProfit(sellProduct.getRevenue()-p.getOriginPrice()* sellProduct.getSellQuantity());
            sellProducts.add(sellProduct);
        }
        return sellProducts;
    }
}
