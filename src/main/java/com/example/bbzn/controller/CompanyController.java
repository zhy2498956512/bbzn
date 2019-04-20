package com.example.bbzn.controller;

import com.example.bbzn.pojo.Company;
import com.example.bbzn.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@ComponentScan
@Controller
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    /*
     * 测试：模拟Session消失
     * */
    /*@RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public void test(HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("Company");
        response.getWriter().print(1);
    }*/

    /*
     * 判断是否有Session
     * */
    /*@RequestMapping(value = "/judgeSession", method = RequestMethod.POST)
    @ResponseBody
    public void test1(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Company company = (Company)request.getSession().getAttribute("Company");
        if(company==null){
            response.getWriter().print(1);
        }else {
            response.getWriter().print(0);
        }
    }*/

    /*
     * 显示公司信息
     * */
    @RequestMapping(value = "/getCompany")
    public String getCompany()  {
        return "user/Information";
    }


    /*
     * 修改公司信息
     * */
    @RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
    public String updateCompany(HttpServletRequest request)  {
        Company company = (Company)request.getSession().getAttribute("Company");
        String companyName = request.getParameter("companyName");
        company.setCompanyName(companyName);
        String companyAddress = request.getParameter("companyAddress");
        company.setCompanyAddress(companyAddress);
        String companyPhone = request.getParameter("companyPhone");
        company.setCompanyPhone(companyPhone);
        if(companyService.updateCompany(company)>0){
            request.getSession().setAttribute("Company",company);
        }
        return "redirect:/api/company/getCompany";
    }

    /*
     * 关闭开启代理商
     * */
    @RequestMapping(value = "/stateCompany", method = RequestMethod.POST)
    @ResponseBody
    public void stateCompany(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String companyId = request.getParameter("companyId");
            String companyType = request.getParameter("companyType");
            int count = companyService.updateCompanyState(Integer.valueOf(companyType),Integer.valueOf(companyId));
            if(count>0){
                response.getWriter().print(1);
            }else {
                response.getWriter().print(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 管理员修改代理商信息显示
     * */
    @RequestMapping(value = "/adminUpdateInformationShow", method = RequestMethod.POST)
    @ResponseBody
    public void adminUpdateInformationShow(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String companyId = request.getParameter("companyId");
            Company company = companyService.getCompanyId(Integer.valueOf(companyId));
            ObjectMapper x = new ObjectMapper();//ObjectMapper类提供方法将list数据转为json数据
            String str1 = x.writeValueAsString(company);
            response.getWriter().print(str1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 管理员修改代理商信息
     * */
    @RequestMapping(value = "/adminUpdateInformation", method = RequestMethod.POST)
    @Transactional
    public String adminUpdateInformation(HttpServletRequest request, HttpServletResponse response)  {
        try {
            String cName = request.getParameter("cName");
            String cPhone = request.getParameter("cPhone");
            String cAddress = request.getParameter("cAddress");
            String cPass = request.getParameter("cPass");
            String cId = request.getParameter("cId");
            Company company = new Company();
            company.setCompanyName(cName);
            company.setCompanyPhone(cPhone);
            company.setCompanyAddress(cAddress);
            company.setCompanyUserPass(cPass);
            company.setCompanyId(Integer.valueOf(cId));
            companyService.updateCompany(company);
            companyService.updatePass(company);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/api/company/getCompanyList";
    }

    /*
     * 修改公司密码
     * */
    @RequestMapping(value = "/updatePass", method = RequestMethod.POST)
    public String updatePass(HttpServletRequest request)  {
        Company company = (Company)request.getSession().getAttribute("Company");
        String newPass = request.getParameter("newPass");
        company.setCompanyUserPass(newPass);
        if(companyService.updatePass(company)>0){
            request.getSession().setAttribute("Company",company);
        }
        return "redirect:/api/company/getCompany";
    }

    /*
     * 代理商列表
     * */
    @RequestMapping(value = "/getCompanyList")
    public String getCompanyList(Model model)  {
        List<Company> list = companyService.getCompanyList();
        model.addAttribute("list",list);
        return "user/CompanyList";
    }


}
