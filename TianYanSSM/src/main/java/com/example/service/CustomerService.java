package com.example.service;

import com.example.entity.Customer;
import com.example.entity.Customershare;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
    List<Customer> findAllCustomers();

    List<Customer> findCustomersByEmpUsername(String username);

    Integer addCustomer(Customer customer);

    Integer removeCustomer(Integer id);

    Integer modifyCustomer(Customer customer);

    Customer findCustomerByCid(int id);

    Customer findCustomerByNameToUpdate(Customer customer);

    Customer findCustomerByNameToInsert(Customer customer);

}
