package app.clothing_management.controller;

import app.clothing_management.model.User;
import app.clothing_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    UserService userService;
    @Autowired
    public  UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping("/api/users")
    public List<User> getAll(){
        return userService.getAllUsers();
    }
    @PostMapping("api/user/login")
    public List<User> Login(@RequestBody User user){
        System.out.println(user.getUsername()+" "+user.getPassword());
        //userService.save(user);
        return userService.getAllUsers();

    }
}
