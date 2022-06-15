package app.clothing_management.repository;

import app.clothing_management.model.Order;
import app.clothing_management.model.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderDetailRepository extends MongoRepository<OrderDetail,String> {
}
