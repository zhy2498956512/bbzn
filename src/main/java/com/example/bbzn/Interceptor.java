package com.example.bbzn;

import com.example.bbzn.pojo.Company;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Company company = (Company)request.getSession().getAttribute("Company");
        if(company==null||company.getCompanyState()==0){
            response.sendRedirect("http://119.23.210.209:80/bbzn/login.jsp");
            return false;
        }else{
            return true;
        }
    }


}
