/*
package com.example.bbzn;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "timerFilter",urlPatterns = {"/api/company/*","/api/companyLanguage/*","/api/equipment/*","/api/equipmentLanguage/*","/api/grant/*",
        "/api/grantNumberRecord/*","/api/grantNumberRecord/*","/api/grantRecord/*","/api/messageVerification/*","/api/project/*","/api/projectLanguage/*","/api/pushRecord/*"})
public class TimerFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getSession().getAttribute("Company")==null){
            String errors = "您还没有登录，或者session已过期。请先登陆!";
            request.setAttribute("Message", errors);
            //跳转至登录页面
            request.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
        }else{
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
*/
