package app.clothing_management.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Document("category")
public class Category {

    @Id
    private String id;
    private String name;
}
