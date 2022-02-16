package com.example.mapper;

import com.example.entity.Customershare;
import com.example.my.mapper.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomershareMapper extends MyMapper<Customershare> {
    List<Customershare> selectCustomerShare();

    List<Customershare> selectAllCustomerShare();

    Integer deleteByCid(int id);

    Customershare selectCustomershareToInsert(Customershare customershare);
}