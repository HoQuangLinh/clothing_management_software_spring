package app.clothing_management.controller;

import app.clothing_management.model.User;
import app.clothing_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    UserService userService;
    @Autowired
    public  UserController(UserService userService){
        this.userService=userService;
    }
    //Lấy tất cả các user
    @GetMapping("/api/users")
    public List<User> getAll(){
        return userService.getAllUsers();
    }
    //Đăng nhập
    @PostMapping("api/users/login")
    public ResponseEntity<?> login(@RequestBody User user){
        return userService.login(user.getUsername(), user.getPassword());
    }
    //Lọc user theo tên, số điện thoại
    @GetMapping("/api/users/filter")
    public List<User> filter(@RequestParam("key") String key){
        return userService.filterUser(key);
    }
    //Tạo user mới
    @PostMapping("/api/users/create")
    public ResponseEntity<String> saveUser(@RequestBody User user){
           return userService.save(user);
    }
    // Cập nhập user
    @PutMapping("/api/users/update/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user,
                                             @PathVariable("id") String id){
        return userService.update(user, id);
    }
}
