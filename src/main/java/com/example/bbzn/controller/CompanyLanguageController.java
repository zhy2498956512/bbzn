package com.example.bbzn.controller;

import com.example.bbzn.pojo.LanguageList;
import com.example.bbzn.pojo.CompanyLanguage;
import com.example.bbzn.pojo.Language;
import com.example.bbzn.service.CompanyLanguageService;
import com.example.bbzn.service.LanguageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/api/companyLanguage")
public class CompanyLanguageController {

    @Autowired
    private CompanyLanguageService companyLanguageService;

    @Autowired
    private LanguageService languageService;

    /*
     * 显示代理商语言列表
     * */
    @RequestMapping(value = "/getCompanyLanguage", method = RequestMethod.POST)
    @ResponseBody
    public void getProjectType(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String company_id = (String) request.getParameter("company_id");
            List<Language> languageList = languageService.getLanguageList();
            List<CompanyLanguage> companyLanguageList = companyLanguageService.getLanguageList(Integer.valueOf(company_id));
            List<Language> listint = new ArrayList<Language>();
            for(int i=0;i<languageList.size();i++){
                for(int z=0;z<companyLanguageList.size();z++){
                    if(languageList.get(i).getLanguageId()==companyLanguageList.get(z).getLanguageId()){
                        listint.add(languageList.get(i));
                    }
                }
            }
            languageList.removeAll(listint);
            LanguageList list = new LanguageList();
            list.setLanguageList(languageList);
            list.setCompanyLanguageList(companyLanguageList);
            String str1 = "";
            ObjectMapper x = new ObjectMapper();//ObjectMapper类提供方法将list数据转为json数据
            str1 = x.writeValueAsString(list);
            response.getWriter().print(str1);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 保存代理商语言列表
     * */
    @Transactional
    @RequestMapping(value = "/saveCompanyLanguage", method = RequestMethod.POST)
    public String saveCompanyLanguage(HttpServletRequest request, HttpServletResponse response)  {
        String languageIdList = (String) request.getParameter("languageIdList");
        String cId = (String) request.getParameter("cId");
        companyLanguageService.deleteLanguage(Integer.valueOf(cId));

        String[] strarray=languageIdList.split(",");
        CompanyLanguage companyLanguage = null;
        for (int i = 0; i < strarray.length; i++){
            if(!"".equals(strarray[i])&&strarray[i]!=null) {
                companyLanguage = new CompanyLanguage();
                companyLanguage.setCompanyId(Integer.valueOf(cId));
                companyLanguage.setLanguageId(Integer.valueOf(strarray[i]));
                companyLanguageService.saveLanguage(companyLanguage);
            }
        }
        return "redirect:/api/company/getCompanyList";
    }

}
