package com.example.mapper;

import com.example.entity.role;
import com.example.my.mapper.MyMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleMapper extends MyMapper<role> {
    public List<String> selectRoleNameByName(String name);

    List<role> selectAllRole();
}