package com.example.mapper;

import com.example.entity.Customervisit;
import com.example.my.mapper.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomervisitMapper extends MyMapper<Customervisit> {
    List<Customervisit> selectAllCustomerVisit();

    List<Customervisit> selectAllCustomerVisitByName(String name);

    Customervisit selectCustomerById(int id);
}