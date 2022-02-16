package com.example.service;

import com.example.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {
    int addNewRole(com.example.entity.RolePermission rolePermission);

    List<RolePermission> findAllRolePermission();

    RolePermission findToInsert(RolePermission rolePermission);

    int removeById(int id);
}
