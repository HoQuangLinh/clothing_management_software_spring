package app.clothing_management.repository;

import app.clothing_management.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> getOrderByDateOrderBetween(Date from, Date to);
    @Query("{$and: [{dateOrder: {$gt : ?0}}, {dateOrder: {$lt : ?1}}]}")
    List<Order> getOrdersByRangeOfDate(Date begin, Date end);
    List<Order> findOrderByStatus(String status);
}
