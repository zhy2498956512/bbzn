package com.example.bbzn.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e,
                                            HandlerMethod method) throws Exception {
        ModelAndView modelAndView = null;
        boolean isApi = method != null && (method.hasMethodAnnotation(ResponseBody.class)
                || method.getBeanType().isAnnotationPresent(RestController.class));
        if (isApi) {
            System.out.println(1);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write("{error: 1}");
            writer.flush();
        } else {
            System.out.println(2);
            System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
            modelAndView = createModelAndView(request, "500", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        return modelAndView;
    }

    private ModelAndView createModelAndView(HttpServletRequest request, String viewName, HttpStatus status, Exception e) {
        ModelAndView mav = new ModelAndView();
        if (e != null) {
            mav.addObject("error", e);
            System.out.println(e.getCause());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
        }
        mav.addObject("url", request.getRequestURI());
        mav.addObject("method", request.getMethod());
        if (status != null) {
            mav.setStatus(status);
        }
        mav.setViewName(viewName);
        return mav;
    }
}
