package com.example.bbzn.controller;

import com.example.bbzn.pojo.*;
import com.example.bbzn.service.CompanyService;
import com.example.bbzn.service.GrantNumberRecordService;
import com.example.bbzn.service.GrantRecordService;
import com.example.bbzn.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/grantNumberRecord")
public class GrantNumberRecordController {

    @Autowired
    private GrantNumberRecordService grantNumberRecordService;

    @Autowired
    private GrantRecordService grantRecordService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CompanyService companyService;

    /*
     * 跳转
     * */
    @RequestMapping(value = "/jump", method = RequestMethod.GET)
    public String jump(HttpServletRequest request, Model model)  {
        Company company = (Company)request.getSession().getAttribute("Company");
        int length1 = 0;
        int length2 = 0;
        List<GrantNumberRecord> list1 = grantNumberRecordService.getGrantNumberRecordList(1,1000000,company.getCompanyId(),1);
        for (GrantNumberRecord g:list1){
            length1+=g.getGrantNumberRecordAmount();
        }
        List<GrantRecord> list2 = grantRecordService.getGrantRecordList(company.getCompanyId());
        for (GrantRecord g:list2){
            length2+=g.getGrantRecordAmount();
        }
        List<Integer> companyId = new ArrayList<Integer>();
        companyId.add(company.getCompanyId());
        List<Project> projectList = projectService.getProjectList(companyId);
        model.addAttribute("projectList",projectList);
        model.addAttribute("length1",length1);
        model.addAttribute("length2",length1-length2);
        return "grant/Information";
    }

    /*
     * 跳转购买页面
     * */
    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String display()  {
        return "grant/GrantNumberRecord";
    }


    /*
     * 跳转待授权
     * */
    @RequestMapping(value = "/toBeAuthorized", method = RequestMethod.GET)
    public String toBeAuthorized(HttpServletRequest request)  {
        return "grant/ToBeAuthorized";
    }


    /*
     * 显示授权、待授权记录列表
     * */
    @RequestMapping(value = "/record")
    @ResponseBody
    public void record(HttpServletRequest request, HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            Page page = new Page();
            Integer recordType = Integer.valueOf(request.getParameter("recordType"));
            Company company = null;
            if(recordType==1){
                company = (Company)request.getSession().getAttribute("Company");
            }else if(recordType==2){
                company = new Company();
            }
            String pn = (String) request.getParameter("pageNum");
            if(pn==null||pn==""){
                pn = "1";
            }
            int pageNum = Integer.valueOf(pn);
            page.setPageSize(3);
            page.setTotalRecord(grantNumberRecordService.getGrantNumberRecordCount(company.getCompanyId(),recordType));
            if(pageNum>page.getTotalPage()){
                pageNum = page.getTotalPage();
            }
            if(pageNum<1){
                pageNum = 1;
            }
            page.setPageNum(pageNum);
            List<GrantNumberRecord> list = grantNumberRecordService.getGrantNumberRecordList(page.getPageNum(),page.getPageSize(),company.getCompanyId(),recordType);
            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制;
            for (GrantNumberRecord g:list){
                if(g.getGrantNumberRecordApplytime()!=null && !"".equals(g.getGrantNumberRecordApplytime())){
                    g.setApplytime(dFormat.format(g.getGrantNumberRecordApplytime()));
                }
                if(recordType!=2){
                    g.setFeedbacktime(dFormat.format(g.getGrantNumberRecordFeedbacktime()));
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
     * 提交购买授权码数量待审核
     * */
    @RequestMapping(value = "/purchaseNumber", method = RequestMethod.POST)
    public String purchaseNumber(HttpServletRequest request)  {
        int grantNumberRecordAmount = Integer.valueOf(request.getParameter("grantNumberRecordAmount"));
        String grantNumberRecordRemark = request.getParameter("grantNumberRecordRemark");
        Company company = (Company)request.getSession().getAttribute("Company");
        GrantNumberRecord grantNumberRecord = new GrantNumberRecord();
        grantNumberRecord.setGrantNumberRecordAmount(grantNumberRecordAmount);
        grantNumberRecord.setGrantNumberRecordRemark(grantNumberRecordRemark);
        grantNumberRecord.setGrantNumberRecordType(2);
        grantNumberRecord.setCompanyId(company.getCompanyId());
        grantNumberRecord.setGrantNumberRecordApplytime(new Date());
        grantNumberRecordService.saveGrantRecord(grantNumberRecord);
        return "redirect:/api/grantNumberRecord/jump";
    }

    /*
     * 管理员直接给予授权码数量
     * */
    @RequestMapping(value = "/giveNumber", method = RequestMethod.POST)
    public String giveNumber(HttpServletRequest request)  {
        int grantNumberRecordAmount = Integer.valueOf(request.getParameter("grantNumberRecordAmount"));
        String grantNumberRecordRemark = request.getParameter("grantNumberRecordRemark");
        String company_id = request.getParameter("company_id");
        GrantNumberRecord grantNumberRecord = new GrantNumberRecord();
        grantNumberRecord.setGrantNumberRecordRemark(grantNumberRecordRemark);
        grantNumberRecord.setGrantNumberRecordAmount(grantNumberRecordAmount);
        grantNumberRecord.setGrantNumberRecordType(1);
        grantNumberRecord.setCompanyId(Integer.valueOf(company_id));
        grantNumberRecord.setGrantNumberRecordFeedbacktime(new Date());
        grantNumberRecordService.saveGrantRecord(grantNumberRecord);
        return "redirect:/api/company/getCompanyList";
    }

    /*
     * 对代理商提交是数量审核
     * */
    @RequestMapping("/getPurchaseFeedback")
    @ResponseBody
    public void getPurchaseFeedback(HttpServletRequest request,HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            int grantNumberRecordId = Integer.valueOf(request.getParameter("grantNumberRecordId"));
            int i = grantNumberRecordService.updateGrantRecord(new Date(),grantNumberRecordId);
            response.getWriter().print(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    * 调整到代理商消息列表页面
    * */
    @RequestMapping("/jumpNewsList")
    public String jumpNewsList(HttpServletRequest request,HttpServletResponse response)  {
        return "grant/GrantNumberRecord";
    }

    /*
    * 代理商查看消息列表
    * */
    @RequestMapping("/getNewsList")
    @ResponseBody
    public void getNewsList(HttpServletRequest request,HttpServletResponse response)  {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            Page page = new Page();
            Company company = (Company)request.getSession().getAttribute("Company");
            String pn = (String) request.getParameter("pageNum");
            if(pn==null||pn==""){
                pn = "1";
            }
            int pageNum = Integer.valueOf(pn);
            page.setPageSize(5);
            page.setTotalRecord(grantNumberRecordService.getSeeCount(company.getCompanyId(),-1));
            if(pageNum>page.getTotalPage()){
                pageNum = page.getTotalPage();
            }
            if(pageNum<1){
                pageNum = 1;
            }
            page.setPageNum(pageNum);
            List<GrantNumberRecord> list = grantNumberRecordService.getSeeList(page.getPageNum(),page.getPageSize(),company.getCompanyId());
            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制;
            for(GrantNumberRecord grantNumberRecord:list){
                if(grantNumberRecord.getGrantNumberRecordApplytime()!=null&&!"".equals(grantNumberRecord.getGrantNumberRecordApplytime())){
                    grantNumberRecord.setApplytime(dFormat.format(grantNumberRecord.getGrantNumberRecordApplytime()));
                }
                if(grantNumberRecord.getGrantNumberRecordFeedbacktime()!=null&&!"".equals(grantNumberRecord.getGrantNumberRecordFeedbacktime())){
                    grantNumberRecord.setFeedbacktime(dFormat.format(grantNumberRecord.getGrantNumberRecordFeedbacktime()));
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

}
