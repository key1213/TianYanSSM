package com.example.mapper;

import com.example.entity.RolePermission;
import com.example.my.mapper.MyMapper;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

@Repository
public interface RolePermissionMapper extends MyMapper<RolePermission> {
    int insertNewPermissionToRole(RolePermission rolePermission);

    List<RolePermission> selectAllRolePermission();

    int deleteById(int id);

    RolePermission selectToInsert(RolePermission rolePermission);
}