package com.example.controller;


import com.example.entity.*;
import com.example.mapper.RoleMapper;
import com.example.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("selectAllRoleToChose")
    public@ResponseBody List<com.example.entity.role> selectAllRoleToChose() {
        List<role> roles = roleMapper.selectAllRole();
        for (role role : roles) {
            System.out.println(role);
        }
        return roles;
    }

    @RequestMapping("/employee:update")
    public ModelAndView employeeUpdate(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Select");
        mav.setViewName("/WEB-INF/employee/update.jsp");
        return mav;
    }

    @RequestMapping("selectAllEmployeeToChose")
    public@ResponseBody List<Employees> selectAllEmployeeToChose() {
        List<Employees> employeeToChose = employeeService.findEmployeeToChose();
        for (Employees employees : employeeToChose) {
            System.out.println(employees);
        }
        return employeeToChose;
    }
    @RequestMapping("selectEmployeeToChoseWithoutName")
    public@ResponseBody List<Employees> selectEmployeeToChoseWithoutName() {
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        List<Employees> employeeToChose = employeeService.findEmployeeToChoseWithoutName(loginAccount);
        for (Employees employees : employeeToChose) {
            System.out.println(employees);
        }
        return employeeToChose;
    }

    @RequestMapping("doEmployeeUpdate")
    public @ResponseBody String doEmployeeUpdate(Employees employees){
        if(employees.getEmptype()== null){
            employees.setEmptype(0);
        }
        System.out.println("待修改的雇员:"+employees);
        Employees employeeByName = employeeService.findEmployeeByNameToUpdate(employees);
        if(employeeByName == null){
            Integer integer = employeeService.modifyEmployee(employees);
            if(integer==1){
                return "ok";
            }else{
                return "no";
            }
        }else{
            return "repeat";
        }
    }

    @RequestMapping("/employee:delete")
    public ModelAndView employeeDelete(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Select");
        mav.setViewName("/WEB-INF/employee/delete.jsp");
        return mav;
    }

    @RequestMapping("/doEmployeeDelete")
    public @ResponseBody String doEmployeeDelete(int id){
        System.out.println("待删除的雇员ID:"+id);
        Integer integer = employeeService.removeEmployee(id);
        if(integer == 1){
            return "ok";
        }
        return "no";
    }

    @RequestMapping("/findEmployeeById")
    public @ResponseBody Employees findEmployeeById(int id){
        Employees allEmployeeById = employeeService.findAllEmployeeById(id);
        System.out.println(allEmployeeById);
        return allEmployeeById;
    }

    @RequestMapping("/employee:insert")
    public ModelAndView employeeInsert(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Select");
        mav.setViewName("/WEB-INF/employee/insert.jsp");
        return mav;
    }

    @RequestMapping("/doEmployeeInsert")
    public void doEmployeeInsert(Employees employees, HttpServletResponse response) throws IOException {
        employees.setPassword("a66abb5684c45962d887564f08346e8d");
        if(employees.getEmptype()== null){
            employees.setEmptype(0);
        }
        System.out.println(employees);
        PrintWriter out = response.getWriter();
        Employees employeeByName = employeeService.findEmployeeByNameToInsert(employees);
        if(employeeByName == null){
            Integer integer = employeeService.addEmployee(employees);
            if(integer==1){
                out.write("<script>alert('Successfully added!');history.back();</script>");
            }else{
                out.write("<script>alert('Failed to add!');history.back();</script>");
            }
        }else {
            out.write("<script>alert('Failed to add!Maybe the username is duplicate.');history.back();</script>");
        }

    }

    @RequestMapping("/employee:select")
    public ModelAndView employeeSelect(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Select");
        mav.setViewName("/WEB-INF/employee/showAll.jsp");
        return mav;
    }

    @RequestMapping("/showEmployeeSelectIn")
    public @ResponseBody
    BootStrapPage<Employees> showEmployeeSelectIn(@RequestBody com.example.entity.Page p) throws IOException {
        System.out.println("Show All Customer--->"+p);
        Page<Employees> page = PageHelper.offsetPage(p.getOffset(), p.getLimit());
        List<Employees> allEmployee = employeeService.findAllEmployee();
        for (Employees employees : allEmployee) {
            System.out.println(employees);
        }
        BootStrapPage<Employees> bootStrapPage = new BootStrapPage<>();
        bootStrapPage.setRows(allEmployee);
        bootStrapPage.setTotal(Integer.parseInt(String.valueOf(page.getTotal())));
        return bootStrapPage;
    }
    @RequestMapping("/showEmployeeQueryIn")
    public @ResponseBody
    BootStrapPage<Employees> showEmployeeQueryIn(@RequestBody com.example.entity.Page p) throws IOException {
        System.out.println("Show All Customer--->"+p);
        Page<Employees> page = PageHelper.offsetPage(p.getOffset(), p.getLimit());
        //得到登录的employee
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(loginAccount);
        List<Employees> allEmployee;
        if("jack".equals(loginAccount)){
            System.out.println("is jack");
            allEmployee = employeeService.findAllEmployee();
        }else{
            System.out.println("not jack");
            allEmployee = employeeService.findAllEmployeeByName(loginAccount);
        }
        for (Employees employees : allEmployee) {
            System.out.println(employees);
        }
        BootStrapPage<Employees> bootStrapPage = new BootStrapPage<>();
        bootStrapPage.setRows(allEmployee);
        bootStrapPage.setTotal(Integer.parseInt(String.valueOf(page.getTotal())));
        return bootStrapPage;
    }

}
