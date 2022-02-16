package com.example.service;

import com.example.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAllPermission();

    List<Permission> findMenuByName(String name);
}
