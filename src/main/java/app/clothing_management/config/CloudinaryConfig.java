package app.clothing_management.config;

import com.cloudinary.Cloudinary;

import com.cloudinary.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryConfig {
  private Cloudinary cloudinary;

  @Autowired
  public CloudinaryConfig(
          @Value("${cloudinary.api_key}") String apiKey,
          @Value("${cloudinary.cloud_name}") String cloudName,
          @Value("${cloudinary.api_secret}") String apiSecret

  ){
    cloudinary= Singleton.getCloudinary();
    cloudinary.config.cloudName=cloudName;
    cloudinary.config.apiSecret=apiSecret;
    cloudinary.config.apiKey=apiKey;

  }

public Map upload(Object file, Map options){
  try {
    return cloudinary.uploader().upload(file,options);
  } catch (IOException e) {
    e.printStackTrace();
    return null;
  }
}


}
