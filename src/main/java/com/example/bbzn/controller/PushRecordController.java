package com.example.bbzn.controller;


import com.example.bbzn.pojo.*;
import com.example.bbzn.service.APKService;
import com.example.bbzn.service.EquipmentService;
import com.example.bbzn.service.GrantService;
import com.example.bbzn.service.PushRecordService;
import com.example.bbzn.util.DataGeneratorUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.Location;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/pushRecord")
public class PushRecordController {


    @Autowired
    private PushRecordService pushRecordService;

    @Autowired
    private APKService apkService;

    @Autowired
    private GrantService grantService;

    @Autowired
    private EquipmentService equipmentService;

    /*
     * 显示推送记录列表
     * */
    @RequestMapping(value = "/showPushRecord")
    @ResponseBody
    public void showPushRecord(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            Page page = new Page();
            String pn = (String) request.getParameter("pageNum");
            String projectId = (String) request.getParameter("projectId");
            String equipmentId = (String) request.getParameter("equipmentId");
            if(pn==null||pn==""){
                pn = "1";
            }
            if(projectId==null||projectId==""){
                projectId = "0";
            }
            if(equipmentId==null||equipmentId==""){
                equipmentId = "0";
            }
            int pageNum = Integer.valueOf(pn);
            page.setPageSize(10);
            page.setTotalRecord(pushRecordService.getPushRecordCount(Integer.valueOf(projectId),Integer.valueOf(equipmentId)));
            if(pageNum>page.getTotalPage()){
                pageNum = page.getTotalPage();
            }
            if(pageNum<1){
                pageNum = 1;
            }
            page.setPageNum(pageNum);
            List<PushRecord> list = pushRecordService.getPushRecordList(page.getPageNum(),page.getPageSize(),Integer.valueOf(projectId),Integer.valueOf(equipmentId));
            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制;
            for(PushRecord pushRecord:list){
                pushRecord.setTimeString(dFormat.format(pushRecord.getPushTime()));
                if(pushRecord.getApkRoute()!=null&&pushRecord.getApkRoute()!=""){
                    String ApkRoute = pushRecord.getApkRoute().substring(pushRecord.getApkRoute().lastIndexOf("/")+1);
                    pushRecord.setApkRoute(ApkRoute.substring(0,10)+"..."+ApkRoute.substring(ApkRoute.length()-10));
                }
            }
            page.setList(list);
            ObjectMapper x = new ObjectMapper();//ObjectMapper类提供方法将list数据转为json数据
            String str1 = x.writeValueAsString(page);
            response.getWriter().print(str1);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * 显示项目推送apk记录
     * */
    @RequestMapping(value = "/showProjectPush")
    @ResponseBody
    public void showProjectPush(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            Page page = new Page();
            String pn = (String) request.getParameter("pageNum");
            String projectId = (String) request.getParameter("projectId");
            if(pn==null||pn==""){
                pn = "1";
            }
            int pageNum = Integer.valueOf(pn);
            page.setPageSize(10);
            page.setTotalRecord(apkService.getPageAPKCount(Integer.valueOf(projectId)));
            if(pageNum>page.getTotalPage()){
                pageNum = page.getTotalPage();
            }
            if(pageNum<1){
                pageNum = 1;
            }
            page.setPageNum(pageNum);
            List<APK> list = apkService.getPageAPKList(page.getPageNum(),page.getPageSize(),Integer.valueOf(projectId));
            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制;
            for(APK apk:list){
                apk.setTimeString(dFormat.format(apk.getUploadDate()));
                if(apk.getApkRoute()!=null&&apk.getApkRoute()!=""){
                    String ApkRoute = apk.getApkRoute().substring(apk.getApkRoute().lastIndexOf("/")+1);
                    apk.setApkRoute(ApkRoute.substring(0,10)+"..."+ApkRoute.substring(ApkRoute.length()-10));
                }
            }
            page.setList(list);
            ObjectMapper x = new ObjectMapper();//ObjectMapper类提供方法将list数据转为json数据
            String str1 = x.writeValueAsString(page);
            response.getWriter().print(str1);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * 显示项目推送apk记录
     * */
    @Transactional
    @RequestMapping(value = "/pushRecord")
    @ResponseBody
    public void pushRecord(HttpServletRequest request, HttpServletResponse response)throws Exception{
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String[] fruit = request.getParameterValues("fruit");
        String[] projectId = request.getParameterValues("projectId");
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> mutilpartFiles = multipartHttpServletRequest.getFiles("file");
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
                    for(int i = 0;i<split.length;i++){
                        if(!"".equals(split[i])){
                            Equipment equipment = equipmentService.getGrantCode(split[i]);
                            PushRecord pushRecord = new PushRecord();
                            pushRecord.setApkId(Integer.valueOf(fruit[0]));
                            pushRecord.setProjectId(Integer.valueOf(projectId[0]));
                            pushRecord.setEquipmentId(equipment.getEquipmentId());
                            pushRecordService.savePushRecord(pushRecord);
                        }
                    }
                    response.getWriter().print("推送成功");

                }else{
                    response.getWriter().print(unavailable);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().print("推送失败!!!");
            }
        }
    }

}
