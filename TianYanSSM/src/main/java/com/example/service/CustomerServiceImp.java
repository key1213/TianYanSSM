package com.example.service;

import com.example.entity.Customer;
import com.example.entity.Customershare;
import com.example.mapper.CustomerMapper;
import com.example.mapper.CustomershareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CustomerServiceImp implements CustomerService{
    @Autowired
    CustomerMapper cm;

    @Autowired
    CustomershareMapper customershareMapper;

    //查找所有用户
    @Override
    public List<Customer> findAllCustomers() {
        return cm.selectAll();
    }

    @Override
    public List<Customer> findCustomersByEmpUsername(String username) {
        return cm.selectCustomerByEmpUsername(username);
    }

    //新增用户
    @Override
    public Integer addCustomer(Customer customer) {
        return cm.insertSelective(customer);
    }

    //删除用户
    @Override
    public Integer removeCustomer(Integer id) {
        //先删除客户和雇员的关系表上的数据，再删除客户表上的数据
        customershareMapper.deleteByCid(id);
        return cm.deleteByPrimaryKey(id);
    }

    //修改用户
    @Override
    public Integer modifyCustomer(Customer customer) {
        return cm.updateByPrimaryKeySelective(customer);
    }

    @Override
    public Customer findCustomerByCid(int id) {
        return cm.selectByPrimaryKey(id);
    }

    @Override
    public Customer findCustomerByNameToUpdate(Customer customer) {
        return cm.selectCustomerByNameToUpdate(customer);
    }

    @Override
    public Customer findCustomerByNameToInsert(Customer customer) {
        return cm.selectCustomerByNameToInsert(customer);
    }


}
