package app.clothing_management.controller;

import app.clothing_management.config.CloudinaryConfig;
import app.clothing_management.model.User;
import app.clothing_management.service.UserService;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class UserController {
    UserService userService;
    @Autowired
    public  UserController(UserService userService){
        this.userService=userService;
    }

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    //Lấy tất cả các user
    @GetMapping("/api/users")
    public List<User> getAll(){
        return userService.getAllUsers();
    }
    //Đăng nhập
    @PostMapping("api/users/login")
    public ResponseEntity<?> login(User user){
        return userService.login(user.getUsername(), user.getPassword());
    }
    //Lọc theo chức vụ
    @GetMapping("/api/users/filterByPositon")
    public  List<User> filterByPosition(@RequestParam("position") String position){
        System.out.println(position);
        return userService.getUsersByPosition(position);
    }

    //Lọc user theo tên, số điện thoại
    @GetMapping("/api/users/filter")
    public List<User> filter(@RequestParam("key") String key){
        return userService.filterUser(key);
    }
    //Tạo user mới
    @PostMapping(value = "/api/users/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUser(User user,
                                           @RequestParam(required = false) MultipartFile image){
        UploadImage(user, image);
        return userService.save(user);
    }
    // xóa user
    @PostMapping("/api/users/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
        return userService.delete(id);
    }
    // Cập nhập user
    @PutMapping("/api/users/update/{id}")
    public ResponseEntity<String> updateUser(User user,
                                             @PathVariable("id") String id
                                            ,@RequestParam(required = false) MultipartFile image){
        UploadImage(user, image);
        return userService.update(user, id);
    }

    private void UploadImage(User user, @RequestParam(required = false) MultipartFile image) {
        if(image!=null){
            Map uploadResult= null;
            try {
                uploadResult = cloudinaryConfig.
                        upload(image.getBytes(), ObjectUtils.asMap("resource_type", "auto", "overwrite", true));
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setImgUrl(uploadResult.get("url").toString());
        }
    }
}
