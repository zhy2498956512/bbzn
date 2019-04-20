package com.example.bbzn.controller;

import com.example.bbzn.pojo.*;
import com.example.bbzn.service.*;
import com.example.bbzn.util.DataGeneratorUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ProjectTypeService projectTypeService;

    @Autowired
    private APKService apkService;

    @Autowired
    private PushRecordService pushRecordService;

    @Value("${apk_url}")
    private String apk_url;


    /*
     * 显示项目类型列表
     * */
    @RequestMapping(value = "/getProjectType", method = RequestMethod.POST)
    @ResponseBody
    public void getProjectType(HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            List<ProjectType> list = projectTypeService.getProjectTypeList();
            String str1 = null;
            if (list.size() != 0) {
                try {
                    ObjectMapper x = new ObjectMapper();
                    str1 = x.writeValueAsString(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            response.getWriter().print(str1);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 新增项目
     * */
    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public String saveProject(HttpServletRequest request)  {
        String projectName = request.getParameter("projectName");
        String projectTypeId = request.getParameter("projectType");
        Company company = (Company)request.getSession().getAttribute("Company");
        Project project = new Project();
        project.setProjectName(projectName);
        project.setProjectTypeId(Integer.valueOf(projectTypeId));
        project.setCompanyId(company.getCompanyId());
        project.setProjectNewdate(new Date());
        projectService.saveProject(project);
        return "redirect:/api/grantNumberRecord/jump";
    }

    /*
     * 修改项目名称
     * */
    @RequestMapping(value = "/updateProjectName")
    public String updateProjectName(HttpServletRequest request)  {
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        String projectName = request.getParameter("projectName");
        try {
            projectService.updateProject(projectName,projectId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/api/project/projectConfigure?projectId="+projectId;
    }

    /*
     * 修改项目名称
     * *//*
    @RequestMapping(value = "/updateName", method = RequestMethod.POST)
    public String updateName(HttpServletRequest request, HttpServletResponse response)  {
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        String projectName = request.getParameter("projectName");
        projectService.updateProject(projectName,projectId);
        return "redirect:/api/grantNumberRecord/jump";
    }*/


    /*
     * 项目上传APK
     * */
    @Transactional
    @RequestMapping(value = "/saveAPK", method = RequestMethod.POST)
    public String addImg(HttpServletRequest req, Model model){
        try {
            Integer projectId = Integer.valueOf((String) req.getParameter("projectId"));
            List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("fileName");
            String edition = (String) req.getParameter("edition");
            MultipartFile file = null;
            APK apk = new APK();
            apk.setApkEdition(Integer.valueOf(edition));
            apk.setProjectId(projectId);
            apk.setUploadDate(new Date());
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    String str1=fileName.substring(0, fileName.indexOf("."));
                    String hz=fileName.substring(fileName.indexOf("."));
                    String updateName= str1+DataGeneratorUtil.timeString()+hz;
                    File filePath = new File(req.getSession().getServletContext().getRealPath("/") + "../apk/", updateName);
                    apk.setApkRoute(apk_url + updateName);
                    file.transferTo(filePath);       //上传文件
                }
            }
            apkService.saveAPK(apk);
            PushRecord pushRecord = null;
            List<Equipment> equipmentList = equipmentService.getProjectIdLookEquipmentList(projectId);
            for (Equipment equipment:equipmentList){
                equipmentService.updateEquipmentAppId(equipment.getEquipmentId(),apk.getApkId());
                pushRecord = new PushRecord();
                pushRecord.setEquipmentId(equipment.getEquipmentId());
                pushRecord.setApkId(apk.getApkId());
                pushRecord.setProjectId(projectId);
                pushRecordService.savePushRecord(pushRecord);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/api/grantNumberRecord/jump";
    }


    /*
     * 项目配置
     * */
    @RequestMapping(value = "/projectConfigure")
    public String projectConfigure(HttpServletRequest req, Model model){
        try {
            Integer projectId = Integer.valueOf((String) req.getParameter("projectId"));
            model.addAttribute("project",projectService.getProject(projectId));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "project/projectConfigure";
    }


    /*
     * 显示项目列表
     * */
    @RequestMapping(value = "/showProjectList")
    @ResponseBody
    public void showProjectList(HttpServletRequest request,HttpServletResponse response){
        try {
            Company company = (Company)request.getSession().getAttribute("Company");
            List<Integer> idList = new ArrayList<Integer>();
            idList.add(company.getCompanyId());
            List<Project> projectList = projectService.getProjectList(idList);
            String str1 = null;
            ObjectMapper x = new ObjectMapper();
            str1 = x.writeValueAsString(projectList);
            response.getWriter().print(str1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /*
     * 批量推送
     * */
    @Transactional
    @RequestMapping(value = "/batchPush")
    @ResponseBody
    public void batchPush(HttpServletRequest request,HttpServletResponse response) {
        try {
            Company company = (Company) request.getSession().getAttribute("Company");
            List<Integer> idList = new ArrayList<Integer>();
            idList.add(company.getCompanyId());
            List<Project> projectList = projectService.getProjectList(idList);
            String str1 = null;
            ObjectMapper x = new ObjectMapper();
            str1 = x.writeValueAsString(projectList);
            response.getWriter().print(str1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
