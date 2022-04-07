package app.clothing_management.service;

import app.clothing_management.model.User;
import app.clothing_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public  User findById(String id){
        Optional<User> userData = userRepository.findById(id);
        return userData.orElse(null);
    }

    public ResponseEntity<String> save(User user){
        try {
            userRepository.save(user);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Save user successfully",HttpStatus.OK);
    }

    public List<User> filterUser(String key) {
        return userRepository.getUsersByNameOrPhone(key);
    }

    public ResponseEntity<String> update(User user, String id){
        try {
            Optional<User> userData = userRepository.findById(id);
            if(userData.isPresent()){
                User _user = userData.get();
                _user.setFullname(user.getFullname());
                _user.setAddress(user.getAddress());
                _user.setBirthday(user.getBirthday());
                _user.setEmail(user.getEmail());
                _user.setGender(user.getGender());
                _user.setPosition(user.getPosition());
                userRepository.save(_user);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Update user successfully",HttpStatus.OK);
    }

    public ResponseEntity<?> login(String username, String password){
        Optional<User> userData = userRepository.getUserByUsername(username);
        if(userData.isPresent()){
            User _user = userData.get();
            if(password.equals(_user.getPassword())){
                return new ResponseEntity<>(_user,HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Password is incorrect",HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Username is not found",HttpStatus.BAD_REQUEST);
    }
}
