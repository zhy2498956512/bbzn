package com.example.bbzn.controller;
import com.example.bbzn.pojo.*;
import com.example.bbzn.service.CompanyService;
import com.example.bbzn.service.GrantRecordService;
import com.example.bbzn.service.GrantService;
import com.example.bbzn.service.ProjectService;
import com.example.bbzn.util.DataGeneratorUtil;
import com.example.bbzn.util.DownloadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/api/grant")
public class GrantController {

    @Autowired
    private GrantService grantService;

    @Autowired
    private GrantRecordService grantRecordService;

    @Autowired
    private ProjectService projectService;

    @Value("${code_url}")
    private String code_url;

    /*
     * 跳转
     * */
    @RequestMapping(value = "/jump", method = RequestMethod.GET)
    public String jump(HttpServletRequest request, Model model) {
        return "";
    }


    /*
     * 显示代理商项目
     * */
    @RequestMapping(value = "/getAgentProject")
    @ResponseBody
    public void getAgentProject(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            Company company = (Company)request.getSession().getAttribute("Company");
            List<Integer> companyId = new ArrayList<Integer>();
            companyId.add(company.getCompanyId());
            List<Project> list = projectService.getProjectList(companyId);
            String str1 = null;
            if (list.size() != 0) {
                ObjectMapper x = new ObjectMapper();//ObjectMapper类提供方法将list数据转为json数据
                str1 = x.writeValueAsString(list);
            }
            response.getWriter().print(str1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 代理商生产授权码
     * */
    @RequestMapping(value = "/getCode")
    @ResponseBody
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        try {
            String prefix = request.getParameter("prefix");       //前缀（最大3个字符或数字）
            int length = Integer.valueOf(request.getParameter("length"));       //授权码长度
            int number = Integer.valueOf(request.getParameter("number"));       //授权码数量
            int binary = Integer.valueOf(request.getParameter("binary"));       //授权码进制（十进制或十六进制）
            List<String> list = new ArrayList<String>();
            StringBuffer result;
            String grantRecordBinary = "";
            if(binary==10){
                grantRecordBinary = "十进制";
            }else{
                grantRecordBinary = "十六进制";
            }
            List<GrantRecord> grantRecordList = grantRecordService.getGrantRecordRuleList(length,grantRecordBinary);
            List<Integer> idlist = new ArrayList<Integer>();
            for(GrantRecord grantRecord:grantRecordList){
                idlist.add(grantRecord.getGrantRecordId());
            }
            for(int z=0;z<number;z++) {
                result = new StringBuffer();
                result.append(prefix);
                for (int i = 0; i < (length-prefix.length()); i++) {
                    result.append(Integer.toHexString(new Random().nextInt(binary)));
                }
                if(grantService.getGrantCode(result.toString(),idlist)<1){
                    list.add(result.toString());
                }else{
                    --z;
                }
            }
            String str1 = null;
            if (list.size() != 0) {
                ObjectMapper x = new ObjectMapper();//ObjectMapper类提供方法将list数据转为json数据
                str1 = x.writeValueAsString(list);
            }
            response.getWriter().print(str1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 代理商将生产的授权码持久化并生成TXT文件
     * */
    @Transactional
    @RequestMapping(value = "/uploadCode", method = RequestMethod.POST)
    @ResponseBody
    public void uploadCode(HttpServletRequest request, HttpServletResponse response){
        try {
            String prefix = request.getParameter("prefix");       //前缀（最大3个字符或数字）
            int length = Integer.valueOf(request.getParameter("length"));       //授权码长度
            int binary = Integer.valueOf(request.getParameter("binary"));       //授权码进制（十进制或十六进制）
            int projectId = Integer.valueOf(request.getParameter("projectId")); //项目id
            String codeList = request.getParameter("codeList");       //授权码集合
            String[] sourceStrArray = codeList.split(",");
            Company company = (Company)request.getSession().getAttribute("Company");
            /* 写入Txt文件*/
            Date date = new Date();
            String formatDate = DataGeneratorUtil.timeString();
            File filePath = new File(request.getSession().getServletContext().getRealPath("/") + "../code_url/"+company.getCompanyUserName()+formatDate+".txt");
            filePath.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
            for(int i = 0; i < sourceStrArray.length; i++){
                out.write(sourceStrArray[i]+"\r\n"); // \r\n即为换行
            }
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件


            //数据持久化
            GrantRecord grantRecord = new GrantRecord();
            grantRecord.setGrantRecordAmount(sourceStrArray.length);
            grantRecord.setGrantRecordPrefix(prefix);
            grantRecord.setGrantRecordLength(length);
            if(binary==10){
                grantRecord.setGrantRecordBinary("十进制");
            }else{
                grantRecord.setGrantRecordBinary("十六进制");
            }
            grantRecord.setGrantRecordSubmittime(new Date());
            grantRecord.setProjectId(projectId);
            grantRecord.setCompanyId(1);
            grantRecord.setGrantRecordFile(code_url+company.getCompanyUserName()+formatDate+".txt");
            grantRecordService.saveGrantRecord(grantRecord);
            Grant grant = null;
            for (int i = 0; i < sourceStrArray.length; i++) {
                grant = new Grant();
                grant.setGrantCode(sourceStrArray[i]);
                grant.setGrantRecordId(grantRecord.getGrantRecordId());
                grantService.saveGrant(grant);
            }
            response.getWriter().print(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 上传序列号文件
     * */
    @RequestMapping(value = "/uploadCodeFile", method = RequestMethod.POST)
    @ResponseBody
    public void uploadCodeFile(HttpServletRequest request,HttpServletResponse response)  {
        /*String[] infos = request.getParameterValues("info");        //项目ID*/
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> mutilpartFiles = multipartHttpServletRequest.getFiles("file");
        for (MultipartFile multipartFile:mutilpartFiles) {
            try {
                byte[] fileBytes = multipartFile.getBytes();
                if (!multipartFile.isEmpty()) {
                    String fileName = multipartFile.getOriginalFilename();
                    String hz=fileName.substring(fileName.indexOf("."));
                    String updateName= DataGeneratorUtil.generateRandomStr(32)+hz;
                    File filePath = new File(request.getSession().getServletContext().getRealPath("/") + "../code_url/", updateName);
                    multipartFile.transferTo(filePath);       //上传文件
                }
                List<String> availableList = new ArrayList<String>();
                List<String> unavailableList = new ArrayList<String>();
                String s = new String(fileBytes);
                String[] split = s.split("\r\n");
                for(int i = 0;i<split.length;i++){
                    if(grantService.getGrantCode(split[i],new ArrayList<>())>0){
                        unavailableList.add(split[i]);
                    }else {
                        availableList.add(split[i]);
                    }
                }
                CodeList list = new CodeList();
                list.setAvailableList(availableList);
                list.setUnavailableList(unavailableList);
                String str1 ;
                ObjectMapper x = new ObjectMapper();//ObjectMapper类提供方法将list数据转为json数据
                str1 = x.writeValueAsString(list);
                response.getWriter().print(str1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /*
     * 代理商将生产的授权码持久化并生成TXT文件（根据序列号文件生成）
     * */
    @Transactional
    @RequestMapping(value = "/uploadFileCode", method = RequestMethod.POST)
    @ResponseBody
    public void uploadFileCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try {
            String codeList = request.getParameter("codeList");       //授权码集合
            String info = request.getParameter("info");               //项目
            String[] sourceStrArray = codeList.split(",");
            Company company = (Company)request.getSession().getAttribute("Company");
            int CompanyId = company.getCompanyId();
            DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //HH表示24小时制;
            Date date = new Date();
            String formatDate = dFormat.format(date);
            File writename = new File(request.getSession().getServletContext().getRealPath("/") + "../code_url/"+company.getCompanyUserName()+formatDate+".txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            GrantRecord grantRecord = new GrantRecord();
            grantRecord.setCompanyId(CompanyId);
            grantRecord.setProjectId(Integer.valueOf(info));
            grantRecord.setGrantRecordAmount(sourceStrArray.length);
            grantRecord.setGrantRecordSubmittime(new Date());
            grantRecord.setGrantRecordFile(code_url+company.getCompanyUserName()+formatDate+".txt");
            grantRecordService.saveGrantRecordFile(grantRecord);
            Grant grant = null;
            for(String s:sourceStrArray){
                grant = new Grant();
                grant.setGrantCode(s);
                grant.setGrantRecordId(grantRecord.getGrantRecordId());
                grantService.saveGrant(grant);
                out.write(s+"\r\n"); // \r\n即为换行
            }
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件

            response.getWriter().print(1);
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().print(0);
        }
    }

}
