package com.example.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("出现异常的方法"+o);
        ModelAndView mav = new ModelAndView();
        if(e instanceof NumberFormatException){
            mav.addObject("msg","只能输入纯数字哦!!!");
        }else if(e instanceof NullPointerException){
            mav.addObject("msg","空指针异常啦!!!");
        }else if(e instanceof ArithmeticException){
            mav.addObject("msg","除数不能为零哦!!!");
        }else{
            mav.addObject("msg",e.getMessage());
        }
        mav.setViewName("/WEB-INF/error.jsp");
        return mav;
    }
}
