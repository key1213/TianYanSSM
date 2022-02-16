package com.example.controller;

import com.example.entity.BootStrapPage;
import com.example.entity.Customer;
import com.example.entity.Customershare;
import com.example.mapper.CustomerMapper;
import com.example.mapper.CustomershareMapper;
import com.example.mapper.EmployeesMapper;
import com.example.service.CustomerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
public class CustomerController {


    CustomerService cs;

    @Autowired
    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    @Autowired
    CustomershareMapper customershareMapper;
    @Autowired
    EmployeesMapper employeesMapper;
    @Autowired
    CustomerMapper customerMapper;

    @RequestMapping("/customer:update")
    public ModelAndView customerUpdate(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Update");

        mav.setViewName("/WEB-INF/customer/update.jsp");
        return mav;
    }

    @RequestMapping("doCustomerUpdate")
    public @ResponseBody String doCustomerUpdate(Customer customer){
        System.out.println("待修改的客户:"+customer);
        Customer customerByName = cs.findCustomerByNameToUpdate(customer);
        System.out.println(customerByName);
        if(customerByName == null){
            Integer integer = cs.modifyCustomer(customer);
            if(integer==1){
                return "ok";
            }else{
                return "no";
            }
        }else{
            return "repeat";
        }
    }


    @RequestMapping("/findCustomerById")
    public @ResponseBody Customer findCustomerById(int id){
        Customer customer = cs.findCustomerByCid(id);
        System.out.println(customer);
        return customer;
    }

    @RequestMapping("selectCustomerToChoseByName")
    public@ResponseBody List<Customer> selectCustomerToChoseByName() {
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(loginAccount);
        List<Customer> customersByEmpUsername;
        if("jack".equals(loginAccount)){
            customersByEmpUsername = cs.findAllCustomers();
        }else{
         customersByEmpUsername = cs.findCustomersByEmpUsername(loginAccount);
        }
        for (Customer customer : customersByEmpUsername) {
            System.out.println(customer);
        }
        return customersByEmpUsername;
    }

    @RequestMapping("/showCustomerQueryIn")
    public @ResponseBody BootStrapPage<Customer> showCustomerQueryIn(@RequestBody com.example.entity.Page p) throws IOException {
        System.out.println("Show All Customer--->"+p);
        //使用分页插件
        Page<Customer> page = PageHelper.offsetPage(p.getOffset(), p.getLimit());
        //得到登录的employee
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(loginAccount);
        List<Customer> customersByEmpUsername;
        if("jack".equals(loginAccount)){
            customersByEmpUsername = cs.findAllCustomers();
        }else {
            customersByEmpUsername = cs.findCustomersByEmpUsername(loginAccount);
        }
        for (Customer customer : customersByEmpUsername) {
            System.out.println(customer);
        }
        BootStrapPage<Customer> bootStrapPage = new BootStrapPage<>();
        bootStrapPage.setRows(customersByEmpUsername);
        bootStrapPage.setTotal(Integer.parseInt(String.valueOf(page.getTotal())));
        return bootStrapPage;
    }

    @RequestMapping("/customer:delete")
    public ModelAndView customerDelete(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Show In");
        mav.setViewName("/WEB-INF/customer/delete.jsp");
        return mav;
    }

    @RequestMapping("/doCustomerDelete")
    public @ResponseBody String doCustomerDelete(int id){
        System.out.println("待删除的客户ID:"+id);
        Integer integer = cs.removeCustomer(id);
        if(integer == 1){
            return "ok";
        }
        return "no";
    }

    @RequestMapping("/customer:insert")
    public ModelAndView customerInsert(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Show In");
        mav.setViewName("/WEB-INF/customer/insert.jsp");
        return mav;
    }

    @RequestMapping("/doCustomerInsert")
    public void doCustomerInsert(Customer customer, HttpServletResponse response) throws IOException {
        System.out.println(customer);
        PrintWriter out = response.getWriter();
        Customer customerByName = cs.findCustomerByNameToInsert(customer);
        //得到登录的employee
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(loginAccount);
        int empid = employeesMapper.selectEmpidByName(loginAccount);
        System.out.println(customerByName);
        if(customerByName == null){
            Integer i = cs.addCustomer(customer);
            int cid = customerMapper.selectCidByName(customer.getCusname());
            if(i==1){
                Customershare customershare = new Customershare();
                customershare.setEmpid(empid);
                customershare.setCid(cid);
                customershareMapper.insertSelective(customershare);
                out.write("<script>alert('Successfully added!');history.back();</script>");
            }else{
                out.write("<script>alert('Failed to add!');history.back();</script>");
            }
        }else{
            out.write("<script>alert('Failed to add!Maybe the cusName is duplicate.');history.back();</script>");
        }
    }

    @RequestMapping("/customer:select")
    public ModelAndView customerSelect(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Select");
        mav.setViewName("/WEB-INF/customer/showAll.jsp");
        return mav;
    }
    @RequestMapping("/showCustomerSelectIn")
    public @ResponseBody BootStrapPage<Customer> showCustomerSelectIn(@RequestBody com.example.entity.Page p) throws IOException {
        System.out.println("Show All Customer--->"+p);
        Page<Customer> page = PageHelper.offsetPage(p.getOffset(), p.getLimit());
        List<Customer> allCustomers = cs.findAllCustomers();
        for (Customer allCustomer : allCustomers) {
            System.out.println(allCustomer);
        }
        BootStrapPage<Customer> bootStrapPage = new BootStrapPage<>();
        bootStrapPage.setRows(allCustomers);
        bootStrapPage.setTotal(Integer.parseInt(String.valueOf(page.getTotal())));
        return bootStrapPage;
    }
}
