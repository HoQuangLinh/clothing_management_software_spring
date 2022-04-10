package app.clothing_management.repository;

import app.clothing_management.model.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDetailRepository extends MongoRepository<OrderDetail,String> {
}
