package app.clothing_management.repository;

import app.clothing_management.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> getOrderByDateOrderBetween(Date from, Date to);
}
