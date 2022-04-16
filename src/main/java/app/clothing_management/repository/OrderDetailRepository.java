package app.clothing_management.repository;

import app.clothing_management.model.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrderDetailRepository extends MongoRepository<OrderDetail,String> {
}
