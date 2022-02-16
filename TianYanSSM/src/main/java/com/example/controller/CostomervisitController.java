package com.example.controller;

import com.example.entity.*;
import com.example.service.CustomervisitService;
import com.example.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

@Controller
public class CostomervisitController {

    @Autowired
    CustomervisitService customervisitService;
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("customervisit:select")
    public ModelAndView customervisitSelect(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Select");
        mav.setViewName("/WEB-INF/customervisit/showAll.jsp");
        return mav;
    }
    @RequestMapping("customervisit:delete")
    public ModelAndView customervisitDelete(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Select");
        mav.setViewName("/WEB-INF/customervisit/delete.jsp");
        return mav;
    }
    @RequestMapping("customervisit:insert")
    public ModelAndView customervisitInsert(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Select");
        mav.setViewName("/WEB-INF/customervisit/insert.jsp");
        return mav;
    }
    @RequestMapping("customervisit:update")
    public ModelAndView customervisitUpdate(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Select");
        mav.setViewName("/WEB-INF/customervisit/update.jsp");
        return mav;
    }

    @RequestMapping("/doCustomervisitInsert")
    public void doCustomervisitInsert(Customervisit customervisit, HttpServletResponse response) throws IOException {
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        Employees employees = new Employees();
        employees.setUsername(loginAccount);
        Employees employeeByNameToInsert = employeeService.findEmployeeByNameToInsert(employees);
        customervisit.setEmpid(employeeByNameToInsert.getEmpid());
        System.out.println(customervisit);
        PrintWriter out = response.getWriter();
        int i = customervisitService.addCustomervisit(customervisit);
        if(i==1){
            out.write("<script>alert('Successfully added!');history.back();</script>");
        }else{
            out.write("<script>alert('Failed to add!');history.back();</script>");
        }
    }

    @RequestMapping("doCustomervisitUpdate")
    public @ResponseBody String doCustomervisitUpdate(Customervisit customervisit){
        System.out.println("待修改的寻访:"+customervisit);
        int integer = customervisitService.modifyCustomervisit(customervisit);
        if(integer==1){
            return "ok";
        }else{
            return "no";
        }

    }

    @RequestMapping("/showCustomervisitSelectIn")
    public @ResponseBody
    BootStrapPage<Customervisit> showCustomervisitSelectIn(@RequestBody com.example.entity.Page p) throws IOException {
        System.out.println("Show All Customer--->"+p);
        Page<Customer> page = PageHelper.offsetPage(p.getOffset(), p.getLimit());
        List<Customervisit> allCustomervisit = customervisitService.findAllCustomervisit();
        for (Customervisit customervisit : allCustomervisit) {
            System.out.println(customervisit);
        }
        BootStrapPage<Customervisit> bootStrapPage = new BootStrapPage<>();
        bootStrapPage.setRows(allCustomervisit);
        bootStrapPage.setTotal(Integer.parseInt(String.valueOf(page.getTotal())));
        return bootStrapPage;
    }

    @RequestMapping("doCustomervisitDelete")
    public @ResponseBody String doCustomervisitDelete(int id){
        System.out.println("待删除的寻访ID:"+id);
        int integer = customervisitService.removeCustomervisit(id);
        if(integer == 1){
            return "ok";
        }
        return "no";
    }

    @RequestMapping("/findCustomervisitById")
    public @ResponseBody Customervisit findCustomervisitById(int id){
        System.out.println("id:--->"+id);
        Customervisit customervisitById = customervisitService.findCustomervisitById(id);
        System.out.println(customervisitById);
        return customervisitById;
    }

    @RequestMapping("/showCustomervisitQueryIn")
    public @ResponseBody
    BootStrapPage<Customervisit> showCustomervisitQueryIn(@RequestBody com.example.entity.Page p) throws IOException {
        System.out.println("Show All Customer--->"+p);
        Page<Customer> page = PageHelper.offsetPage(p.getOffset(), p.getLimit());
        //得到登录的employee
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(loginAccount);
        List<Customervisit> allCustomervisit ;
        if("jack".equals(loginAccount)){
            allCustomervisit = customervisitService.findAllCustomervisit();
        }else {
            allCustomervisit = customervisitService.findAllCustomervisitByName(loginAccount);
        }
        for (Customervisit customervisit : allCustomervisit) {
            System.out.println(customervisit);
        }
        BootStrapPage<Customervisit> bootStrapPage = new BootStrapPage<>();
        bootStrapPage.setRows(allCustomervisit);
        bootStrapPage.setTotal(Integer.parseInt(String.valueOf(page.getTotal())));
        return bootStrapPage;
    }
}
