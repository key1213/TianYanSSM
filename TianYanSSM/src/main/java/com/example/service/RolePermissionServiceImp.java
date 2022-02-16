package com.example.service;

import com.example.entity.RolePermission;
import com.example.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolePermissionServiceImp implements RolePermissionService{
    RolePermissionMapper rolePermissionMapper;

    @Autowired
    public void setRolePermissionMapper(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    //为当前用户增加新的权限
    @Override
    public int addNewRole(RolePermission rolePermission) {
        return rolePermissionMapper.insertNewPermissionToRole(rolePermission);
    }

    @Override
    public List<RolePermission> findAllRolePermission() {
        return rolePermissionMapper.selectAllRolePermission();
    }

    @Override
    public RolePermission findToInsert(RolePermission rolePermission) {
        return rolePermissionMapper.selectToInsert(rolePermission);
    }

    @Override
    public int removeById(int id) {
        return rolePermissionMapper.deleteById(id);
    }
}
