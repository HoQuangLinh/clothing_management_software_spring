package app.clothing_management.service;

import app.clothing_management.model.User;
import app.clothing_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            String password = user.getPassword();
            String hassPassword = hashMD5(password);
            user.setPassword(hassPassword);
            userRepository.save(user);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Save user successfully",HttpStatus.OK);
    }

    public List<User> filterUser(String key) {
        return userRepository.getUsersByNameOrPhone(key);
    }
    public List<User> getUsersByPosition(String position){
        return userRepository.getUsersByPosition(position);
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
                if(user.getImgUrl()!=null){
                    _user.setImgUrl(user.getImgUrl());
                }

                userRepository.save(_user);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Update user successfully",HttpStatus.OK);
    }

    public ResponseEntity<?> login(String username, String password){
        try {
            Optional<User> userData = userRepository.getUserByUsername(username);
            if(userData.isPresent()){
                User _user = userData.get();
                String hassPassword = hashMD5(password);
                if(hassPassword.equals(_user.getPassword())){
                    return new ResponseEntity<>(_user,HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Password is incorrect",HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>("Username is not found",HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String hashMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest msg = MessageDigest.getInstance("MD5");
        byte[] hash = msg.digest(str.getBytes(StandardCharsets.UTF_8));
        // convert bytes to hexadecimal
        StringBuilder s = new StringBuilder();
        for (byte b : hash) {
            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return s.toString();
    }

    public ResponseEntity<String> delete(String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return new ResponseEntity<>("Delete user successfully",HttpStatus.OK);
        }
        return null;
    }
}
