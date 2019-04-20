package com.example.bbzn.controller;

import com.example.bbzn.pojo.*;
import com.example.bbzn.service.EquipmentLanguageService;
import com.example.bbzn.service.EquipmentService;
import com.example.bbzn.service.GrantService;
import com.example.bbzn.service.ProjectLanguageService;
import com.example.bbzn.util.DataGeneratorUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/equipmentLanguage")
public class EquipmentLanguageController {

    @Autowired
    private EquipmentLanguageService equipmentLanguageService;

    @Autowired
    private ProjectLanguageService projectLanguageService;

    @Autowired
    private GrantService grantService;

    @Autowired
    private EquipmentService equipmentService;

    /*
     * 显示项目语言列表
     * */
    @RequestMapping(value = "/getEquipmentLanguage", method = RequestMethod.POST)
    @ResponseBody
    public void getProjectLanguage(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String projectId = (String) request.getParameter("projectId");
            String equipmentId = (String) request.getParameter("equipmentId");
            List<ProjectLanguage> projectLanguageList = projectLanguageService.getLanguageList(Integer.valueOf(projectId));
            List<EquipmentLanguage> equipmentLanguageList = equipmentLanguageService.getEquipmentLanguageList(Integer.valueOf(equipmentId));
            List<ProjectLanguage> listint = new ArrayList<ProjectLanguage>();
            for(int i=0;i<projectLanguageList.size();i++){
                for(int z=0;z<equipmentLanguageList.size();z++){
                    if(projectLanguageList.get(i).getLanguageId()==equipmentLanguageList.get(z).getLanguageId()){
                        listint.add(projectLanguageList.get(i));
                    }
                }
            }
            projectLanguageList.removeAll(listint);

            LanguageList list = new LanguageList();
            list.setLanguageList(projectLanguageList);
            list.setCompanyLanguageList(equipmentLanguageList);
            String str1 = "";
            ObjectMapper x = new ObjectMapper();//ObjectMapper类提供方法将list数据转为json数据
            str1 = x.writeValueAsString(list);
            response.getWriter().print(str1);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * 保存设备语言列表
     * */
    @Transactional
    @RequestMapping(value = "/saveEquipmentLanguage", method = RequestMethod.POST)
    public String saveCompanyLanguage(HttpServletRequest request, HttpServletResponse response)  {
        String projectId = (String) request.getParameter("projectId");
        try {
            String languageIdList = (String) request.getParameter("languageIdList");
            String equipmentId = (String) request.getParameter("cId");
            List<EquipmentLanguage> equipmentLanguageList = equipmentLanguageService.getEquipmentLanguageList(Integer.valueOf(equipmentId));
            String[] strarray=languageIdList.split(",");
            List<Integer> list = new ArrayList<Integer>();
            List<Integer> list1 = new ArrayList<Integer>();
            List<Integer> list2 = new ArrayList<Integer>();
            for(int i = 0; i < strarray.length; i++){
                if(!"".equals(strarray[i])){
                    list1.add(Integer.valueOf(strarray[i]));
                }
            }
            for(EquipmentLanguage projectLanguage:equipmentLanguageList){
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
            EquipmentLanguage equipmentLanguage = null;
            list1.removeAll(list);
            list2.removeAll(list);
            for(Integer integer:list1) {     //增加语言
                equipmentLanguage = new EquipmentLanguage();
                equipmentLanguage.setEquipmentId(Integer.valueOf(equipmentId));
                equipmentLanguage.setLanguageId(integer);
                equipmentLanguageService.saveEquipmentLanguage(equipmentLanguage);
            }
            for(Integer integer:list2) {     //减少语言
                equipmentLanguage = new EquipmentLanguage();
                equipmentLanguage.setEquipmentId(Integer.valueOf(equipmentId));
                equipmentLanguage.setLanguageId(integer);
                equipmentLanguageService.deleteEquipmentLanguage(equipmentLanguage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/api/equipment/jumpEquipment?projectId="+projectId+"&equipmentType=1&searchEquipment=";
    }

    /*
     * 序列号文件配置语言
     * */
    @Transactional
    @RequestMapping(value = "/configurationLanguage", method = RequestMethod.POST)
    @ResponseBody
    public void configurationLanguage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String[] fruit = request.getParameterValues("multiselect_to_2");
        String[] projectId = request.getParameterValues("projectId1");
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> mutilpartFiles = multipartHttpServletRequest.getFiles("file1");
        for (MultipartFile multipartFile:mutilpartFiles) {
            try {
                byte[] fileBytes = multipartFile.getBytes();
                String s = new String(fileBytes);
                String unavailable = "以下序列号不可用,请检测:";
                String[] split = s.split("\r\n");
                for(int i = 0;i<split.length;i++){
                    if(!"".equals(split[i])){
                        int count = grantService.getAvailability(split[i],Integer.valueOf(projectId[0]));
                        if(count<1){
                            unavailable = unavailable + split[i] + ",";
                        }
                    }
                }
                if(unavailable.equals("以下序列号不可用,请检测:")){
                    EquipmentLanguage equipmentLanguage = null;
                    for(int i = 0;i<split.length;i++){
                        if(!"".equals(split[i])){
                            Equipment equipment = equipmentService.getGrantCode(split[i]);
                            equipmentLanguageService.deleteSingleEquipmentLanguage(equipment.getEquipmentId());
                            for(int z = 0;z<fruit.length;z++){
                                equipmentLanguage = new EquipmentLanguage();
                                equipmentLanguage.setEquipmentId(equipment.getEquipmentId());
                                equipmentLanguage.setLanguageId(Integer.valueOf(fruit[z]));
                                equipmentLanguageService.saveEquipmentLanguage(equipmentLanguage);
                            }
                        }
                    }
                    response.getWriter().print("配置成功");
                }else{
                    response.getWriter().print(unavailable);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().print("配置失败!!!");
            }
        }
    }

}
