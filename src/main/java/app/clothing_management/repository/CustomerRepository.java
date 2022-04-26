package app.clothing_management.repository;

import app.clothing_management.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    //lọc customer theo tên hoặc số điện thoại
    @Query("{$or:[{_id: {$regex: ?0, $options: i}},{name: {$regex: ?0, $options: i}},{phone: {$regex: ?0, $options: i}}]}")
    List<Customer> getCustomersByNameOrPhone(String key);
    //lộc customer theo khoảng điểm tích lũy
    @Query("{$and: [{point: {$gte : ?0}}, {point: {$lte : ?1}}]}")
    List<Customer> getCustomerByRangeOfPoint(int minPoint, int maxPoint);
    //lộc customer theo khoảng điểm tích lũy
    @Query("{$and: [{totalPrice: {$gte : ?0}}, {totalPrice: {$lte : ?1}}]}")
    List<Customer> getCustomerByRangeOfTotalPrice(int minTotal, int maxTotal);
}
