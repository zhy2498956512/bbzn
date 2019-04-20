/*
package com.example.bbzn.util;

import com.example.bbzn.pojo.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * 切面类
 *//*

@Aspect
@Component
public class RequestAspect {

    @Autowired
    private CommonResponse commonResponse;

    */
/**
     * Pointcut定义切点
     * public修饰符的   返回值任意  com.cy.controller包下面的任意类的任意方法任意参数
     *//*

    @Pointcut("execution(public * com.example.bbzn.controller.UserAPIController.*(..))")
    public void log(){

    }

    @Before("log()")
    public String doBefore(){
        User user = (User) request.getSession().getAttribute("User");
        if(user==null){
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
        return "";
    }

}
*/
