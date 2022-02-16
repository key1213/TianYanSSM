package com.example.controller;

import com.example.entity.*;
import com.example.mapper.RoleMapper;
import com.example.mapper.RolePermissionMapper;
import com.example.service.PermissionService;
import com.example.service.RolePermissionService;
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
public class RolePermissionController {

    @Autowired
    RolePermissionService rolePermissionService;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleMapper roleMapper;

    @RequestMapping("/rolepermission:insert")
    public ModelAndView RolePermissionInsert(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Show In");
        mav.setViewName("/WEB-INF/rolepermission/insert.jsp");
        return mav;
    }
    @RequestMapping("/rolepermission:select")
    public ModelAndView RolePermissionSelect(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Select");
        mav.setViewName("/WEB-INF/rolepermission/showAll.jsp");
        return mav;
    }
    @RequestMapping("/rolepermission:delete")
    public ModelAndView RolePermissionDelete(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Show In");
        mav.setViewName("/WEB-INF/rolepermission/delete.jsp");
        return mav;
    }
    @RequestMapping("/rolepermission:update")
    public ModelAndView RolePermissionUpdate(){
        ModelAndView mav = new ModelAndView();
        System.out.println("Update");

        mav.setViewName("/WEB-INF/rolepermission/update.jsp");
        return mav;
    }

    @RequestMapping("/doRolePermissionDelete")
    public @ResponseBody String doRolePermissionDelete(int id){
        System.out.println("待删除的权限ID:"+id);
        int i = rolePermissionService.removeById(id);
        if(i == 1){
            return "ok";
        }
        return "no";
    }

    @RequestMapping("selectRoleToChose")
    public@ResponseBody List<com.example.entity.role> selectRoleToChose() {
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(loginAccount);
        List<role> roles = roleMapper.selectAllRole();
        for (role role : roles) {
            System.out.println(role);
        }
        return roles;
    }
    @RequestMapping("selectPermissionToChose")
    public@ResponseBody List<Permission> selectPermissionToChose() {
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(loginAccount);
        List<Permission> allPermission = permissionService.findAllPermission();
        for (Permission permission : allPermission) {
            System.out.println(permission);
        }
        return allPermission;
    }

    @RequestMapping("/showRolePermissionIn")
    public @ResponseBody BootStrapPage<RolePermission> showRolePermissionIn(@RequestBody com.example.entity.Page p) throws IOException {
        System.out.println("Show All Customer--->"+p);
        Page<Customer> page = PageHelper.offsetPage(p.getOffset(), p.getLimit());
        List<RolePermission> allRolePermission = rolePermissionService.findAllRolePermission();
        for (RolePermission rolePermission : allRolePermission) {
            System.out.println(rolePermission);
        }
        BootStrapPage<RolePermission> bootStrapPage = new BootStrapPage<>();
        bootStrapPage.setRows(allRolePermission);
        bootStrapPage.setTotal(Integer.parseInt(String.valueOf(page.getTotal())));
        return bootStrapPage;
    }
//    @RequestMapping("doRolePermissionUpdate")
//    public @ResponseBody String doRolePermissionUpdate(Customer customer){
//        System.out.println("待修改的客户:"+customer);
//        Customer customerByName = cs.findCustomerByNameToUpdate(customer);
//        System.out.println(customerByName);
//        if(customerByName == null){
//            Integer integer = cs.modifyCustomer(customer);
//            if(integer==1){
//                return "ok";
//            }else{
//                return "no";
//            }
//        }else{
//            return "repeat";
//        }
//    }
//
//
//    @RequestMapping("/findRolePermissionById")
//    public @ResponseBody Customer findRolePermissionById(int id){
//        Customer customer = cs.findCustomerByCid(id);
//        System.out.println(customer);
//        return customer;
//    }
//
//    @RequestMapping("selectRolePermissionToChoseByName")
//    public@ResponseBody List<Customer> selectRolePermissionToChoseByName() {
//        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
//        System.out.println(loginAccount);
//        List<Customer> customersByEmpUsername;
//        if("jack".equals(loginAccount)){
//            customersByEmpUsername = cs.findAllCustomers();
//        }else{
//         customersByEmpUsername = cs.findCustomersByEmpUsername(loginAccount);
//        }
//        for (Customer customer : customersByEmpUsername) {
//            System.out.println(customer);
//        }
//        return customersByEmpUsername;
//    }
//

//
//
//
    @RequestMapping("/doRolePermissionInsert")
    public void doRolePermissionInsert(RolePermission rolePermission, HttpServletResponse response) throws IOException {
        System.out.println(rolePermission);
        PrintWriter out = response.getWriter();
        List<Integer> list = rolePermission.getList();
        RolePermission rp = new RolePermission();
        for (Integer integer : list) {
            rp.setPermissionId(integer);
            rp.setRoleId(rolePermission.getRoleId());
            RolePermission toInsert = rolePermissionService.findToInsert(rolePermission);
            if(toInsert==null){
                rolePermissionService.addNewRole(rp);
            }
        }
        out.write("<script>alert('Successfully added!');history.back();</script>");
    }



}
