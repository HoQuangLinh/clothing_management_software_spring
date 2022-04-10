package app.clothing_management.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("users")
@Getter
@Setter
public class User {
    @Id
    private  String id;
    private  String username;
    private  String password;
    private  String fullname;
    private  String phone;
    private  String address;
    private  String email;
    private  String gender;
    private  String imgUrl;
    private  String position;
    private  Date birthday;
}
