package com.example.service;

import com.example.entity.Employees;
import com.example.mapper.EmployeesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService{

    EmployeesMapper employeesMapper;

    @Autowired
    public void setEmployeesMapper(EmployeesMapper employeesMapper) {
        this.employeesMapper = employeesMapper;
    }

    //查询所有雇员信息
    @Override
    public List<Employees> findAllEmployee() {
        return employeesMapper.selectAllEmployee();
    }

    @Override
    public List<Employees> findEmployeeToChose() {
        return employeesMapper.selectEmployeeToChose();
    }

    @Override
    public List<Employees> findAllEmployeeByName(String name) {
        return employeesMapper.selectAllEmployeeByName(name);
    }

    @Override
    public List<Employees> findEmployeeToChoseWithoutName(String name) {
        return employeesMapper.selectEmployeeToChoseWithoutName(name);
    }

    @Override
    public Employees findAllEmployeeById(int id) {
        return employeesMapper.selectAllEmployeeById(id);
    }

    @Override
    public Employees findEmployeeByNameToInsert(Employees employees) {
        return employeesMapper.selectEmployeeByNameToInsert(employees);
    }

    @Override
    public Employees findEmployeeByNameToUpdate(Employees employees) {
        return employeesMapper.selectEmployeeByNameToUpdate(employees);
    }

    //新增雇员
    @Override
    public Integer addEmployee(Employees employees) {
        return employeesMapper.insertSelective(employees);
    }

    //删除雇员
    @Override
    public Integer removeEmployee(int id) {
        return employeesMapper.deleteByPrimaryKey(id);
    }
    //修改雇员信息
    @Override
    public Integer modifyEmployee(Employees employees) {
        return employeesMapper.updateByPrimaryKeySelective(employees);
    }

    @Override
    public int modifyPasswordByName(Employees employees) {
        return employeesMapper.updatePasswordByName(employees);
    }
}
