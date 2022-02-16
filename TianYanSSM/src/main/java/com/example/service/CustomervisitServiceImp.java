package com.example.service;

import com.example.entity.Customervisit;
import com.example.mapper.CustomerMapper;
import com.example.mapper.CustomervisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomervisitServiceImp implements CustomervisitService{

    CustomervisitMapper customervisitMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    public void setCustomervisitMapper(CustomervisitMapper customervisitMapper) {
        this.customervisitMapper = customervisitMapper;
    }

    //查询所有寻访记录
    @Override
    public List<Customervisit> findAllCustomervisit() {
        return customervisitMapper.selectAllCustomerVisit();
    }

    @Override
    public List<Customervisit> findAllCustomervisitByName(String name) {
        return customervisitMapper.selectAllCustomerVisitByName(name);
    }

    @Override
    public Customervisit findCustomervisitById(int id) {
        return customervisitMapper.selectCustomerById(id);
    }

    //按id删除寻访记录
    @Override
    public int removeCustomervisit(int id) {
        return customervisitMapper.deleteByPrimaryKey(id);
    }

    //新增寻访记录
    @Override
    public int addCustomervisit(Customervisit customervisit) {

        return customervisitMapper.insertSelective(customervisit);
    }

    //修改寻访记录
    @Override
    public int modifyCustomervisit(Customervisit customervisit) {
        return customervisitMapper.updateByPrimaryKeySelective(customervisit);
    }
}
