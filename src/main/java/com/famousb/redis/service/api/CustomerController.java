package com.famousb.redis.service.api;

import com.famousb.redis.service.model.Customer;
import com.famousb.redis.service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/customer")
    public ResponseEntity<String> saveCustomer(@RequestBody @Valid Customer customer){
        try {
            boolean isSaved = customerService.saveCustomer(customer);
            if(!isSaved){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok("Customer created successfully");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping(path = "/all")
    public ResponseEntity<List<Customer>> fetchAllCustomer(){
        try {
            List<Customer> customers = customerService.fetchAllCustomer();
            return ResponseEntity.ok(customers);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id){
        try {
            Customer customers = customerService.geCustomerById(id);
            return ResponseEntity.ok(customers);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path = "/customer/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try {
            boolean customers = customerService.deleteCustomerById(id);
            if (!customers){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok("Customer deleted successfully");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(path = "/update/customer/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        try {
            boolean customers = customerService.updateCustomer(id, customer);
            if (!customers){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok("Customer updated successfully");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
