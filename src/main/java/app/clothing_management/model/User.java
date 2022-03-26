package app.clothing_management.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Document("users")
public class User {
    @Id
    private  String id;
    private  String username;
    private  String password;
    private  String fullname;
    List<Product> products=new ArrayList<>();
    public void clearProduct(){
        this.products.clear();
    }
}
