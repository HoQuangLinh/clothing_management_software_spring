package app.clothing_management.repository;

import app.clothing_management.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    //lọc customer theo tên hoặc số điện thoại
    @Query("{$or:[{name: /?0/i},{phone: /?0/i}]}")
    List<Customer> getCustomersByNameOrPhone(String key);
    //lộc customer theo khoảng điểm tích lũy
    @Query("{$and: [{point: {$gt : ?0}}, {point: {$lt : ?1}}]}")
    List<Customer> getCustomerByRangeOfPoint(int minPoint, int maxPoint);
}
