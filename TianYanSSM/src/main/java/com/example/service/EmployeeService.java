package com.example.service;

import com.example.entity.Employees;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<Employees> findAllEmployee();

    List<Employees> findEmployeeToChose();

    List<Employees> findAllEmployeeByName(String name);

    List<Employees> findEmployeeToChoseWithoutName(String name);

    Employees findAllEmployeeById(int id);

    Employees findEmployeeByNameToInsert(Employees employees);

    Employees findEmployeeByNameToUpdate(Employees employees);

    Integer addEmployee(Employees employees);

    Integer removeEmployee(int id);

    Integer modifyEmployee(Employees employees);

    int modifyPasswordByName(Employees employees);
}
