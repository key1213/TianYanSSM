package com.example.service;

import com.example.entity.Permission;
import com.example.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImp implements PermissionService{
    @Autowired
    private PermissionMapper p;

    @Override
    public List<Permission> findAllPermission() {
        return p.selectAll();
    }

    @Override
    public List<Permission> findMenuByName(String name) {
        return p.selectMenuByName(name);
    }
}
