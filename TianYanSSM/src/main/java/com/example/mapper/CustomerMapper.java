package com.example.mapper;

import com.example.entity.Customer;
import com.example.my.mapper.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMapper extends MyMapper<Customer> {
    List<Customer> selectCustomerByEmpUsername(String name);

    int selectCidByName(String name);

    Customer selectCustomerByNameToUpdate(Customer customer);

    Customer selectCustomerByNameToInsert(Customer customer);
}