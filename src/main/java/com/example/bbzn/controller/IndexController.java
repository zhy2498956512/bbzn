package com.example.bbzn.controller;


import com.example.bbzn.pojo.Company;
import com.example.bbzn.pojo.GrantNumberRecord;
import com.example.bbzn.pojo.Page;
import com.example.bbzn.service.CompanyService;
import com.example.bbzn.service.GrantNumberRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/api/login")
public class IndexController {


    @Autowired
    private CompanyService companyService;

    @Autowired
    private GrantNumberRecordService grantNumberRecordService;

    /*
    * 登录入口
    * */
    @RequestMapping("/")
    public String login()  {
        return "login";
    }

    /*
     * 登录验证
     * */
    @RequestMapping(value = "/loginVerification", method = RequestMethod.POST)
    @ResponseBody
    public void loginVerification(HttpServletRequest request, HttpServletResponse response, Model model)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String companyUserName = request.getParameter("companyUserName");
            String companyUserPass = request.getParameter("companyUserPass");
            Company company =companyService.getCompany(companyUserName,companyUserPass);
            if(company!=null){
                if(company.getCompanyState()==1){
                    request.getSession().setAttribute("Company",company);
                    response.getWriter().print(1);
                }else {
                    response.getWriter().print(2);
                }
            }else{
                response.getWriter().print(0);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
     * 首页
     * */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model)  {
        try {
            Page page = new Page();
            Company company = (Company)request.getSession().getAttribute("Company");
            if(company.getCompanyUserType()==0){
                page.setPageSize(1);
                page.setTotalRecord(grantNumberRecordService.getSeeCount(company.getCompanyId(),0));
                List<GrantNumberRecord> list = grantNumberRecordService.getSeeList(1, 4, company.getCompanyId());
                DateFormat dFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss"); //HH表示24小时制;
                for(GrantNumberRecord grantNumberRecord:list){
                    if(grantNumberRecord.getGrantNumberRecordApplytime()!=null&&!"".equals(grantNumberRecord.getGrantNumberRecordApplytime())){
                        grantNumberRecord.setApplytime(dFormat.format(grantNumberRecord.getGrantNumberRecordApplytime()));
                    }
                    if(grantNumberRecord.getGrantNumberRecordFeedbacktime()!=null&&!"".equals(grantNumberRecord.getGrantNumberRecordFeedbacktime())){
                        grantNumberRecord.setFeedbacktime(dFormat.format(grantNumberRecord.getGrantNumberRecordFeedbacktime()));
                    }
                }
                page.setList(list);
            }
            model.addAttribute("page", page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    /*
     * 注销
     * */
    @RequestMapping(value = "/quit")
    public String quit(HttpServletRequest request)  {
        request.getSession().removeAttribute("Company");
        return "login";
    }

}
