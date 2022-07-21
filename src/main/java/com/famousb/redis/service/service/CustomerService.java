package com.famousb.redis.service.service;

import com.famousb.redis.service.model.Customer;
import com.famousb.redis.service.model.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public boolean saveCustomer(Customer customer){
        try {
           boolean isSave = customerRepository.saveCustomer(customer);
           if (!isSave){
               return false;
           }
           return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public List<Customer> fetchAllCustomer(){
        try {
            List<Customer> customers = customerRepository.fetchAllCustomer();
            return customers;
        }catch (Exception ex){
            ex.printStackTrace();
            throw  new RuntimeException("An error occurred");
        }
    }

    public Customer geCustomerById(Long id){
        try {
            Customer customer = customerRepository.getById(id);
            return customer;
        }catch (Exception ex){
            ex.printStackTrace();
            throw  new RuntimeException("An error occurred");
        }
    }

    public boolean deleteCustomerById(Long id){
        try {
            boolean customer = customerRepository.deleteById(id);
            if(!customer){
                return false;
            }
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            throw  new RuntimeException("An error occurred");
        }
    }

    public boolean updateCustomer(Long id, Customer customer){
        try {
            boolean isUpdate = customerRepository.updateCustomer(id,customer);
            if(!isUpdate){
                return false;
            }
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            throw  new RuntimeException("An error occurred");
        }
    }
}
