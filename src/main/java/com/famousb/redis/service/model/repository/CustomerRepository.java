package com.famousb.redis.service.model.repository;

import com.famousb.redis.service.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CustomerRepository extends Customer {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "CUSTOMER";

    public boolean saveCustomer(Customer customer){
        try {
           redisTemplate.opsForHash().put(KEY, customer.getId().toString(), customer);
           return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public List<Customer> fetchAllCustomer(){
        try {
            List<Customer> customers;
            customers = redisTemplate.opsForHash().values(KEY);
            return customers;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("An error occurred");
        }
    }

    public Customer getById(Long id){
        try {
           Customer customer = (Customer) redisTemplate.opsForHash().get(KEY, id.toString());
           return customer;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("An error occurred");
        }
    }

    public boolean deleteById(Long id){
        try {
            Long deleted = redisTemplate.opsForHash().delete(KEY, id.toString());
            if(deleted != null){
                return true;
            }
            return false;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("An error occurred");
        }
    }

    public boolean updateCustomer(Long id, Customer customer){
        try {
            Customer existingCustomer = (Customer) redisTemplate.opsForHash().get(KEY, id.toString());
            if(existingCustomer != null){
                Customer customer1 = new Customer();
                customer1.setId(customer.getId() != null ? customer.getId() : existingCustomer.getId());
                customer1.setDob(customer.getDob() != null ? customer.getDob() : existingCustomer.getDob());
                customer1.setFirstName(customer.getFirstName() != null ? customer.getFirstName() : existingCustomer.getFirstName());
                customer1.setLastName(customer.getLastName() != null ? customer.getLastName() : existingCustomer.getLastName());
                customer1.setEmail(customer.getEmail() != null ? customer.getEmail() : existingCustomer.getEmail());

                Long deleted = redisTemplate.opsForHash().delete(KEY, customer1);
                if(deleted != null){
                    redisTemplate.opsForHash().put(KEY, id.toString(), customer1);
                    return true;
                }
            }
            return false;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("An error occurred");
        }
    }

}
