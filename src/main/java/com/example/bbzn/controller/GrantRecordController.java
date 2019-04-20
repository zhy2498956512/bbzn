package com.example.bbzn.controller;

import com.example.bbzn.pojo.Company;
import com.example.bbzn.service.CompanyService;
import com.example.bbzn.service.GrantRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/grantRecord")
public class GrantRecordController {

    @Autowired
    private GrantRecordService grantRecordService;

    @Autowired
    private CompanyService companyService;

    /*
     * 显示公司信息
     * */
    @RequestMapping(value = "/getCompany")
    public String getCompany()  {
        return "user/Information";
    }

    

}
