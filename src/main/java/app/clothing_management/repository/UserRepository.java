package app.clothing_management.repository;

import app.clothing_management.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    @Query("{$or:[{id: ?0},{fullname : {$regex : ?0, $options: 'i'}},{phone :{$regex : ?0}}]}")
    List<User> getUsersByNameOrPhone(String key);

    @Query("{username : ?0}")
    Optional<User> getUserByUsername(String username);
    @Query("{position : ?0}")
    List<User> getUsersByPosition(String position);

}
