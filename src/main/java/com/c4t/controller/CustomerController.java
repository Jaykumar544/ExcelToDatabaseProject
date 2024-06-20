package com.c4t.controller;

import com.c4t.entity.Customer;
import com.c4t.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController
{

    @Autowired
    CustomerRepository customerRepository;


    @GetMapping("/customers")
    public List<Customer> gettingCustomers()
    {
        return customerRepository.findAll();
    }

    @PostMapping("/customer")
    public String createCustomer(@RequestBody Customer customer)
    {
        customerRepository.save(customer);
        return "Customer Record saved.";
    }
    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable String id)
    {
        customerRepository.deleteById(id);
        return "Record Deleted.";
    }
}
