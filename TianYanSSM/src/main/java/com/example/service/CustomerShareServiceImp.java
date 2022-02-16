package com.example.service;

import com.example.entity.Customershare;
import com.example.mapper.CustomershareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class CustomerShareServiceImp implements CustomerShareService{

    @Autowired
    CustomershareMapper customershareMapper;

    @Override
    public List<Customershare> findAllCustomershare() {
        return customershareMapper.selectCustomerShare();
    }

    @Override
    public List<Customershare> findAll() {
        return customershareMapper.selectAllCustomerShare();
    }

    @Override
    public Customershare findCustomershareToInsert(Customershare customershare) {
        return customershareMapper.selectCustomershareToInsert(customershare);
    }
}
