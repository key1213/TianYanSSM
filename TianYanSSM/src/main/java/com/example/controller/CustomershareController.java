package com.example.controller;


import com.example.entity.BootStrapPage;
import com.example.entity.Customer;
import com.example.entity.Customershare;
import com.example.mapper.CustomershareMapper;
import com.example.service.CustomerShareService;
import com.github.pagehelper.PageHelper;
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
public class CustomershareController {
    @Autowired
    CustomerShareService customerShareService;
    @Autowired
    CustomershareMapper customershareMapper;

    @RequestMapping("/showCustomershareSelectIn")
    public @ResponseBody
    BootStrapPage<Customershare> showCustomervisitSelectIn(@RequestBody com.example.entity.Page p) throws IOException {
        System.out.println("Show All Customer--->"+p);
        com.github.pagehelper.Page<Customer> page = PageHelper.offsetPage(p.getOffset(), p.getLimit());
//        List<Customershare> customershares = customershareMapper.selectAll();
        List<Customershare> customershares1 = customerShareService.findAll();
        for (Customershare customershare : customershares1) {
            System.out.println(customershare);
        }
//        for (Customershare customershare : customershares) {
//            System.out.println(customershare);
//        }
        BootStrapPage<Customershare> bootStrapPage = new BootStrapPage<>();
        bootStrapPage.setRows(customershares1);
        bootStrapPage.setTotal(Integer.parseInt(String.valueOf(page.getTotal())));
        return bootStrapPage;
    }

    @RequestMapping("doCustomershareDelete")
    public @ResponseBody String doCustomervisitDelete(int id){
        System.out.println("待删除的共享ID:"+id);
        int integer = customershareMapper.deleteByPrimaryKey(id);
        if(integer == 1){
            return "ok";
        }
        return "no";
    }

    @RequestMapping("/doCustomershareInsert")
    public void doCustomershareInsert(Customershare customershare, HttpServletResponse response) throws IOException {
        System.out.println(customershare);
        PrintWriter out = response.getWriter();
        Customershare customershareToInsert = customerShareService.findCustomershareToInsert(customershare);
        if(customershareToInsert == null){
            int i = customershareMapper.insertSelective(customershare);
            if(i==1){
                out.write("<script>alert('Successfully added!');history.back();</script>");
            }else{
                out.write("<script>alert('Failed to add!');history.back();</script>");
            }
        }else{
            out.write("<script>alert('Failed to add!Employee already owns this user.');history.back();</script>");
        }
    }
    @RequestMapping("customershare:select")
    public ModelAndView customershareSelect(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/WEB-INF/customershare/showAll.jsp");
        return mav;
    }
    @RequestMapping("customershare:delete")
    public ModelAndView customershareDelete(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/WEB-INF/customershare/delete.jsp");
        return mav;
    }
    @RequestMapping("customershare:insert")
    public ModelAndView customershareInsert(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/WEB-INF/customershare/insert.jsp");
        return mav;
    }
    @RequestMapping("customershare:update")
    public ModelAndView customershareUpdate(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/WEB-INF/customershare/update.jsp");
        return mav;
    }

}
