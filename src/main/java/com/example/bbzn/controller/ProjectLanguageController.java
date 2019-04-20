package com.example.bbzn.controller;

import com.example.bbzn.pojo.*;
import com.example.bbzn.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/projectLanguage")
public class ProjectLanguageController {

    @Autowired
    private LanguageService languageService;

    @Autowired
    private CompanyLanguageService companyLanguageService;

    @Autowired
    private ProjectLanguageService projectLanguageService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentLanguageService equipmentLanguageService;

    /*
     * 显示项目语言列表
     * */
    @RequestMapping(value = "/getProjectLanguage", method = RequestMethod.POST)
    @ResponseBody
    public void getProjectLanguage(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            Company company = (Company)request.getSession().getAttribute("Company");
            String projectId = (String) request.getParameter("projectId");
            List<CompanyLanguage> companyLanguageList = companyLanguageService.getLanguageList(company.getCompanyId());
            List<ProjectLanguage> projectLanguageList = projectLanguageService.getLanguageList(Integer.valueOf(projectId));
            List<CompanyLanguage> listint = new ArrayList<CompanyLanguage>();
            for(int i=0;i<companyLanguageList.size();i++){
                for(int z=0;z<projectLanguageList.size();z++){
                    if(companyLanguageList.get(i).getLanguageId()==projectLanguageList.get(z).getLanguageId()){
                        listint.add(companyLanguageList.get(i));
                    }
                }
            }
            companyLanguageList.removeAll(listint);
            LanguageList list = new LanguageList();
            list.setLanguageList(companyLanguageList);
            list.setCompanyLanguageList(projectLanguageList);
            String str1 = "";
            ObjectMapper x = new ObjectMapper();//ObjectMapper类提供方法将list数据转为json数据
            str1 = x.writeValueAsString(list);
            response.getWriter().print(str1);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * 项目下所有语言列表
     * */
    @RequestMapping(value = "/getProjectLanguageList", method = RequestMethod.POST)
    @ResponseBody
    public void getProjectLanguageList(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String projectId = (String) request.getParameter("projectId");
            List<ProjectLanguage> projectLanguageList = projectLanguageService.getLanguageList(Integer.valueOf(projectId));
            String str1 = "";
            ObjectMapper x = new ObjectMapper();//ObjectMapper类提供方法将list数据转为json数据
            str1 = x.writeValueAsString(projectLanguageList);
            response.getWriter().print(str1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 保存代理商语言列表
     * */
    @Transactional
    @RequestMapping(value = "/saveProjectLanguage", method = RequestMethod.POST)
    public String saveCompanyLanguage(HttpServletRequest request, HttpServletResponse response)  {
        try {
            String languageIdList = (String) request.getParameter("languageIdList");
            String projectId = (String) request.getParameter("cId");
            List<ProjectLanguage> projectLanguageList = projectLanguageService.getLanguageList(Integer.valueOf(projectId));
            String[] strarray=languageIdList.split(",");
            List<Integer> list = new ArrayList<Integer>();
            List<Integer> list1 = new ArrayList<Integer>();
            List<Integer> list2 = new ArrayList<Integer>();
            for(int i = 0; i < strarray.length; i++){
                if(!"".equals(strarray[i])){
                    list1.add(Integer.valueOf(strarray[i]));
                }
            }
            for(ProjectLanguage projectLanguage:projectLanguageList){
                list2.add(projectLanguage.getLanguageId());
            }
            for (int i = 0; i < list1.size(); i++){
                if(!"".equals(list1.get(i))) {
                    for (int y = 0; y < list2.size(); y++) {
                        if (list1.get(i) == list2.get(y)) {
                            list.add(list1.get(i));
                        }
                    }
                }
            }
            list1.removeAll(list);
            list2.removeAll(list);
            ProjectLanguage projectLanguage = null;
            List<Integer> idlist = new ArrayList<Integer>();
            idlist.add(Integer.valueOf(projectId));
            List<Equipment> equipmentList = equipmentService.getEquipmentList(idlist,"","",0,"",1,100000);
            EquipmentLanguage equipmentLanguage = null;
            for(Integer integer:list1){     //增加语言
                projectLanguage = new ProjectLanguage();
                projectLanguage.setProjectId(Integer.valueOf(projectId));
                projectLanguage.setLanguageId(integer);
                projectLanguageService.saveProjectLanguage(projectLanguage);
                for(Equipment equipment:equipmentList){
                    equipmentLanguage = new EquipmentLanguage();
                    equipmentLanguage.setEquipmentId(equipment.getEquipmentId());
                    equipmentLanguage.setLanguageId(integer);
                    equipmentLanguageService.saveEquipmentLanguage(equipmentLanguage);
                }
            }
            for(Integer integer:list2){     //减少语言
                projectLanguage = new ProjectLanguage();
                projectLanguage.setProjectId(Integer.valueOf(projectId));
                projectLanguage.setLanguageId(integer);
                projectLanguageService.deleteProjectLanguage(projectLanguage);
                for(Equipment equipment:equipmentList){
                    equipmentLanguage = new EquipmentLanguage();
                    equipmentLanguage.setEquipmentId(equipment.getEquipmentId());
                    equipmentLanguage.setLanguageId(integer);
                    equipmentLanguageService.deleteEquipmentLanguage(equipmentLanguage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/api/grantNumberRecord/jump";
    }
}
