package com.example.mapper;

import com.example.entity.Employees;
import com.example.my.mapper.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesMapper extends MyMapper<Employees> {
    String selectPasswordByName(String name);

    int updatePasswordByName(Employees employees);

    int selectEmpidByName(String name);

    List<Employees> selectAllEmployee();

    List<Employees> selectEmployeeToChose();

    List<Employees> selectAllEmployeeByName(String name);

    List<Employees> selectEmployeeToChoseWithoutName(String name);

    Employees selectAllEmployeeById(int id);

    Employees selectEmployeeByNameToUpdate(Employees employees);

    Employees selectEmployeeByNameToInsert(Employees employees);
}