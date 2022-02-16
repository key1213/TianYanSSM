package com.example.mapper;

import com.example.entity.Permission;
import com.example.my.mapper.MyMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermissionMapper extends MyMapper<Permission> {
    List<String> selectPermissionByName(String name);

    List<Permission> selectMenuByName(String name);
}