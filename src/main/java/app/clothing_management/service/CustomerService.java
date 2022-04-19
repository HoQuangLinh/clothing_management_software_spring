package app.clothing_management.service;

import app.clothing_management.model.Customer;
import app.clothing_management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public Customer getCustomerById(String id){return customerRepository.findById(id).get();}
    public List<Customer> getAllCustomers(){return customerRepository.findAll();}
    public Customer createCustomer(Customer customer){return customerRepository.save(customer);}
    public List<Customer> getCustomersByNameOrPhone(String key){
        return customerRepository.getCustomersByNameOrPhone(key);
    }
    public List<Customer> getCustomerByRangeOfPoint(int minPoint, int maxPoint){
        return customerRepository.getCustomerByRangeOfPoint(minPoint,maxPoint);
    }
    public Customer getCustomerMaxPoint(){
        return customerRepository.findAll(Sort.by(Sort.Direction.DESC, "point")).get(0);
    }
    public List<Customer> getCustomerByRangeOfTotalPrice(int minTotal, int maxTotal){
        return customerRepository.getCustomerByRangeOfTotalPrice(minTotal, maxTotal);
    }
}
