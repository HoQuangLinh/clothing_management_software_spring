package app.clothing_management.controller;

import app.clothing_management.model.Customer;
import app.clothing_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    //Lấy tất cả customer
    @GetMapping()
    public List<Customer> getAllCustomer(){return customerService.getAllCustomers();}
    //Tạo customer
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        try {
            System.out.println(customer);
            customerService.createCustomer(customer);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //Lọc customer theo tên hoặc số điện thoại
    @GetMapping("/filter")
    public List<Customer> filterCustomerByNameOrPhone(@RequestParam("key") String key){
        return customerService.getCustomersByNameOrPhone(key);
    }
    //Lọc customer theo khoảng điểm tích lũy
    @GetMapping("/filterPoint")
    public List<Customer> filterCustomerByRangeOfPoint(@RequestParam("minPoint") int minPoint,
                                                       @RequestParam("maxPoint") int maxPoint){
        return customerService.getCustomerByRangeOfPoint(minPoint, maxPoint);
    }
    //Lấy Top 1 customer theo điểm tích lũy
    @GetMapping("/filterMaxPoint")
    public Customer filterCustomerMaxPoint(){
        return customerService.getCustomerMaxPoint();
    }
}
