package com.example.bbzn.controller;

import com.example.bbzn.pojo.*;
import com.example.bbzn.pushAPI.model.PushWork;
import com.example.bbzn.pushAPI.push.PushClient;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentLanguageService equipmentLanguageService;

    @Autowired
    private APKService apkService;

    @Autowired
    private UserService userService;

    @Autowired
    private GrantService grantService;

    @Autowired
    private GrantRecordService grantRecordService;

    @Autowired
    private ProjectLanguageService projectLanguageService;

    @Autowired
    private PushRecordService pushRecordService;

    @Value("${apk_url}")
    private String apk_url;

    /*
     * 跳转
     * */
    @RequestMapping(value = "/jump", method = RequestMethod.GET)
    public String jump(HttpServletRequest request, Model model) {
        String UserType = request.getParameter("UserType");
        List<Company> list = null;
        if(Integer.valueOf(UserType)==1){
            list = companyService.getCompanyList();
        }else if(Integer.valueOf(UserType)==0){
            list = new ArrayList<Company>();
            Company company = (Company)request.getSession().getAttribute("Company");
            list.add(company);
        }
        model.addAttribute("CompanyList",list);
        return "equipment/Equipment";
    }

    /*
     * 跳转
     * */
    @RequestMapping(value = "/getProjectList", method = RequestMethod.POST)
    @ResponseBody
    public void getProjectList(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String multiselect_to_1 = request.getParameter("multiselect_to_1");
            List<Integer> intlist = new ArrayList<Integer>();
            String[] strarray=multiselect_to_1.split(",");
            for (int i = 0; i < strarray.length; i++){
                intlist.add(Integer.valueOf(strarray[i]));
            }
            List<Project> list = null;
            if (multiselect_to_1 != null && multiselect_to_1 != "") {
                list = projectService.getProjectList(intlist);
            }
            String str1 = null;
            if (list.size() != 0) {
                ObjectMapper x = new ObjectMapper();
                try {
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
     * 代理商点击项目名跳转设备页面
     * */
    @RequestMapping(value = "/jumpEquipment")
    public String jumpEquipment(HttpServletRequest request,Model model){
        try {
            Page page = new Page();
            Integer projectId = Integer.valueOf(request.getParameter("projectId"));
            String equipmentType = (String) request.getParameter("equipmentType");          //设备状态
            String searchEquipment = (String) request.getParameter("searchEquipment");      //搜索ID
            String pn = (String) request.getParameter("pageNum");
            if(pn==null||pn==""){
                pn = "1";
            }
            int pageNum = Integer.valueOf(pn);
            page.setPageSize(5);
            List<Integer> list = new ArrayList<Integer>();
            list.add(projectId);
            if(equipmentType==null){
                equipmentType = "0";
            }
            if(searchEquipment==null){
                searchEquipment = "";
            }
            page.setTotalRecord(equipmentService.getEquipmentCount(list,"","",Integer.valueOf(equipmentType),searchEquipment));
            if(pageNum>page.getTotalPage()){
                pageNum = page.getTotalPage();
            }
            if(pageNum<1){
                pageNum = 1;
            }
            page.setPageNum(pageNum);
            List<Equipment> equipmentList = equipmentService.getEquipmentList(list,"","",Integer.valueOf(equipmentType),searchEquipment,page.getPageNum(),page.getPageSize());
            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(Equipment equipment:equipmentList){
                if(equipment.getEquipmentFoundtime()!=null){
                    equipment.setFoundtime(dFormat.format(equipment.getEquipmentFoundtime()));
                }
                if(equipment.getEquipmentLogintime()!=null){
                    equipment.setLogintime(dFormat.format(equipment.getEquipmentLogintime()));
                }
            }
            List<APK> apks = apkService.getAPKList();
            for(Equipment equipment:equipmentList){
                for(APK apk:apks){
                    if(apk.getApkId()==equipment.getApkId()){
                        equipment.setApkEdition(apk.getApkEdition());
                    }
                }
            }
            page.setList(equipmentList);
            model.addAttribute("page",page);
            model.addAttribute("projectId",projectId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "equipment/EquipmentList";
    }

    /*
     * 设备页点击上下页查询
     * */
    @RequestMapping(value = "/jumpEquipmentJson")
    @ResponseBody
    public void jumpEquipmentJson(HttpServletRequest request,HttpServletResponse response){
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            Page page = new Page();
            Integer projectId = Integer.valueOf(request.getParameter("projectId"));
            String equipmentType = (String) request.getParameter("equipmentType");          //设备状态
            String searchEquipment = (String) request.getParameter("searchEquipment");      //搜索ID
            if(searchEquipment==null||"".equals(searchEquipment)){
                searchEquipment = "";
            }
            String pn = (String) request.getParameter("pageNum");
            if(pn==null||pn==""){
                pn = "1";
            }
            int pageNum = Integer.valueOf(pn);
            page.setPageSize(5);
            List<Integer> list = new ArrayList<Integer>();
            list.add(projectId);
            page.setTotalRecord(equipmentService.getEquipmentCount(list,"","",Integer.valueOf(equipmentType),searchEquipment));
            if(pageNum>page.getTotalPage()){
                pageNum = page.getTotalPage();
            }
            if(pageNum<1){
                pageNum = 1;
            }
            page.setPageNum(pageNum);
            List<Equipment> equipmentList = equipmentService.getEquipmentList(list,"","",Integer.valueOf(equipmentType),searchEquipment,page.getPageNum(),page.getPageSize());
            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(Equipment equipment:equipmentList){
                if(equipment.getEquipmentFoundtime()!=null){
                    equipment.setFoundtime(dFormat.format(equipment.getEquipmentFoundtime()));
                }
                if(equipment.getEquipmentLogintime()!=null){
                    equipment.setLogintime(dFormat.format(equipment.getEquipmentLogintime()));
                }
            }
            List<APK> apks = apkService.getAPKList();
            for(Equipment equipment:equipmentList){
                for(APK apk:apks){
                    if(apk.getApkId()==equipment.getApkId()){
                        equipment.setApkEdition(apk.getApkEdition());
                    }
                }
            }
            page.setList(equipmentList);
            String str1 = null;
            ObjectMapper x = new ObjectMapper();
            str1 = x.writeValueAsString(page);
            response.getWriter().print(str1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 查询
     * */
    @RequestMapping(value = "/getEquipment", method = RequestMethod.POST)
    @ResponseBody
    public void getEquipment(HttpServletRequest request,HttpServletResponse response) {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            Page page = new Page();
            String multiselect_to_2 = (String) request.getParameter("multiselect_to_2");
            String date = (String) request.getParameter("date");
            String multiselect_to_3 = "";
            String multiselect_to_4 = "";
            if(date!=null&&!"".equals(date)&&!"".equals(" ~ ")){
                multiselect_to_3 = date.substring(0, date.indexOf(" ~ "));//截取@之前的字符串
                multiselect_to_4 = date.substring(date.indexOf(" ~ ")+3);
                if(multiselect_to_3!=null&&!"".equals(multiselect_to_3)) multiselect_to_3+=" 00:00:00";
                else multiselect_to_3="";
                if(multiselect_to_4!=null&&!"".equals(multiselect_to_4)) multiselect_to_4+=" 00:00:00";
                else multiselect_to_4="";
            }
            if(multiselect_to_2==null) multiselect_to_2="";
            String pn = (String) request.getParameter("pageNum");
            if(pn==null||"".equals(pn)) pn = "1";
            int pageNum = Integer.valueOf(pn);
            page.setPageSize(10);
            List<Integer> intlist = new ArrayList<Integer>();
            String[] strarray = null;
            if(multiselect_to_2!=null&&!"".equals(multiselect_to_2)) strarray=multiselect_to_2.split(",");
            else strarray=new String[0];
            for (int i = 0; i < strarray.length; i++) intlist.add(Integer.valueOf(strarray[i]));
            page.setTotalRecord(equipmentService.getEquipmentCount(intlist,multiselect_to_3,multiselect_to_4,0,""));
            if(pageNum>page.getTotalPage()) pageNum = page.getTotalPage();
            if(pageNum<1) pageNum = 1;
            page.setPageNum(pageNum);
            List<Equipment> list = equipmentService.getEquipmentList(intlist,multiselect_to_3,multiselect_to_4,0,"",page.getPageNum(),page.getPageSize());
            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<APK> apks = apkService.getAPKList();
            for(Equipment equipment:list){
                if(equipment.getEquipmentFoundtime()!=null) equipment.setFoundtime(dFormat.format(equipment.getEquipmentFoundtime()));
                if(equipment.getEquipmentLogintime()!=null) equipment.setLogintime(dFormat.format(equipment.getEquipmentLogintime()));
                for(APK apk:apks) if(equipment.getApkId()==apk.getApkId()) equipment.setApkEdition(apk.getApkEdition());
            }
            page.setList(list);
            String str1 = null;
            ObjectMapper x = new ObjectMapper();
            str1 = x.writeValueAsString(page);
            response.getWriter().print(str1);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 删除查询
     * */
    @RequestMapping(value = "/deleteEquipment", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public void deleteEquipment(HttpServletRequest request,HttpServletResponse response) {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String equipmentId = (String) request.getParameter("equipmentId");
            Equipment equipment = equipmentService.getEquipment(Integer.valueOf(equipmentId));
            equipmentLanguageService.deleteSingleEquipmentLanguage(Integer.valueOf(equipmentId));
            int i = equipmentService.deleteEquipment(Integer.valueOf(equipmentId));
            userService.updateUserGrant(equipment.getGrantId());
            response.getWriter().print(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 新增设备
     * */
    @RequestMapping(value = "/saveEquipment", method = RequestMethod.POST)
    @ResponseBody
    public void saveEquipment(HttpServletRequest request,HttpServletResponse response){
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String grantCode = (String) request.getParameter("grantCode");
            String projectId = (String) request.getParameter("projectId");
            Company company = (Company)request.getSession().getAttribute("Company");
            Grant grant = grantService.getGrant(grantCode);
            if(grant != null){
                Equipment equipment1 = equipmentService.getGrant(grant.getGrantId());
                if(equipment1==null){
                    GrantRecord grantRecord = grantRecordService.getGrantRecord(grant.getGrantRecordId());
                    if(grantRecord.getCompanyId()==company.getCompanyId()){
                        Equipment equipment = new Equipment();
                        equipment.setEquipmentFoundtime(new Date());
                        equipment.setProjectId(Integer.valueOf(projectId));
                        equipment.setGrantId(grant.getGrantId());
                        equipment.setCompanyId(company.getCompanyId());
                        int count = equipmentService.saveEquipment(equipment);
                        int equipmentId = equipment.getEquipmentId();
                        if(count>0){
                            List<ProjectLanguage> list = projectLanguageService.getLanguageList(Integer.valueOf(projectId));
                            EquipmentLanguage e = null;
                            for(ProjectLanguage p:list){
                                e = new EquipmentLanguage();
                                e.setEquipmentId(equipmentId);
                                e.setLanguageId(p.getLanguageId());
                                equipmentLanguageService.saveEquipmentLanguage(e);
                            }
                            response.getWriter().print(1);          //新增成功
                        }else{
                            response.getWriter().print(-1);          //系统异常
                        }
                    }else{
                        response.getWriter().print(2);          //授权码不属于该代理商
                    }
                }else{
                    response.getWriter().print(3);          //授权码已使用
                }
            }else{
                response.getWriter().print(0);          //授权码不存在
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /*
     * 设备上传APK
     * */
    @RequestMapping(value = "/saveAPK", method = RequestMethod.POST)
    @Transactional
    public String addImg(HttpServletRequest req, Model model){
        Integer equipmentId = Integer.valueOf((String) req.getParameter("equipmentId"));
        Integer projectId = Integer.valueOf((String) req.getParameter("projectId"));
        try {
            PushWork pushWork = new PushWork();
            PushClient pushClient = new PushClient();
            pushWork.setContent("推送成功!!!");
            Integer[] plats = {1};
            pushWork.setPlats(plats);
            pushWork.setTarget(1);
            pushWork.setType(1);
            pushClient.sendPush(pushWork);

            List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("fileName");
            String edition = (String) req.getParameter("edition");
            MultipartFile file = null;
            APK apk = new APK();
            apk.setUploadDate(new Date());
            apk.setApkEdition(Integer.valueOf(edition));
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
            equipmentService.updateEquipmentAppId(equipmentId,apk.getApkId());
            PushRecord pushRecord = new PushRecord();
            pushRecord.setEquipmentId(equipmentId);
            pushRecord.setApkId(apk.getApkId());
            pushRecord.setProjectId(projectId);
            pushRecordService.savePushRecord(pushRecord);
        }catch (Exception e){
            e.printStackTrace();
            PushRecord pushRecord = new PushRecord();
            pushRecord.setEquipmentId(666);
            pushRecord.setApkId(666);
            pushRecord.setProjectId(666);
            pushRecordService.savePushRecord(pushRecord);
        }
        return "redirect:/api/equipment/jumpEquipment?projectId="+projectId;
    }

    /*
     * 设备配置
     * */
    @RequestMapping(value = "/equipmentConfigure")
    public String equipmentConfigure(HttpServletRequest req, Model model){
        try {
            Integer equipmentId = Integer.valueOf((String) req.getParameter("equipmentId"));
            String projectId = (String) req.getParameter("projectId");
            Equipment equipment = equipmentService.getEquipment(equipmentId);
            int edition = 0;
            APK apk = apkService.getApkEdition(equipmentId);
            if(apk!=null){
                edition = apk.getApkEdition();
            }
            model.addAttribute("equipment",equipment);
            model.addAttribute("projectId",projectId);
            model.addAttribute("edition",edition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "equipment/EquipmentConfigure";
    }


    /*
     * 设备更换项目
     * */
    @Transactional
    @RequestMapping(value = "/equipmentSwitchProject", method = RequestMethod.POST)
    @ResponseBody
    public void equipmentSwitchProject(HttpServletRequest request,HttpServletResponse response) throws Exception {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            Integer equipmentId = Integer.valueOf((String) request.getParameter("equipmentId"));
            Integer projectId = Integer.valueOf((String) request.getParameter("projectId"));
            Integer switchProjectId = Integer.valueOf((String) request.getParameter("switchProjectId"));
            equipmentService.equipmentSwitchProject(switchProjectId,equipmentId);       //更新设备表
            equipmentLanguageService.deleteSingleEquipmentLanguage(equipmentId);        //删除语言
            List<ProjectLanguage> projectLanguageList = projectLanguageService.getLanguageList(switchProjectId);
            EquipmentLanguage equipmentLanguage = null;
            for(ProjectLanguage projectLanguage:projectLanguageList){
                equipmentLanguage = new EquipmentLanguage();
                equipmentLanguage.setLanguageId(projectLanguage.getLanguageId());
                equipmentLanguage.setEquipmentId(equipmentId);
                equipmentLanguageService.saveEquipmentLanguage(equipmentLanguage);
            }
            response.getWriter().print(200);
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().print(0);
        }
    }

}
