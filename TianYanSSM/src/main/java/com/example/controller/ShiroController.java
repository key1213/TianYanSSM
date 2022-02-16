package com.example.controller;

import com.example.entity.Employees;
import com.example.entity.Permission;
import com.example.service.EmployeeService;
import com.example.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/shiro")
public class ShiroController {
    @Autowired
    EmployeeService employeeService;

    PermissionService p;

    @Autowired
    public void setP(PermissionService p) {
        this.p = p;
    }

    @RequestMapping("/main")
    public ModelAndView main(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/WEB-INF/main.jsp");
        return mav;
    }

    @RequestMapping("/loginOut")
    public ModelAndView loginOut(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/index.jsp");
        return mav;
    }
    @RequestMapping("/changePassword")
    public @ResponseBody String changePassword(String password){
        Md5Hash md5Hash2 = new Md5Hash(password,"admin");
        String s = md5Hash2.toString();
        Employees employees = new Employees();
        employees.setPassword(s);
        String loginAccount = SecurityUtils.getSubject().getPrincipal().toString();
        employees.setUsername(loginAccount);
        System.out.println(employees);
        int i = employeeService.modifyPasswordByName(employees);
        if(i==1){
            return "ok";
        }else{
            return "no";
        }
    }

    @RequestMapping("/login")
    public ModelAndView login(Employees employees){
        System.out.println("----->    "+employees);
        ModelAndView mav = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(employees.getUsername(),employees.getPassword()));
            if(subject.isAuthenticated()){
                System.out.println("---  通过  ---");
                List<Permission> name = p.findMenuByName(employees.getUsername());
                for (Permission permission : name) {
                    System.out.println(permission);
                }
                mav.addObject("list", name);
                mav.setViewName("/WEB-INF/main.jsp");
                return mav;
            }
        }catch(UnknownAccountException e){
            System.out.println("账号出错");
        }catch(IncorrectCredentialsException e){
            System.out.println("密码出错");
        }

        mav.setViewName("/WEB-INF/LoginError.jsp");
        return mav;
    }
}
