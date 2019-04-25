package com.example.bbzn.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bbzn.pojo.Grant;
import com.example.bbzn.service.GrantService;
import com.example.bbzn.util.CommonResponse;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private GrantService grantService;

    @Autowired
    private CommonResponse commonResponse;


    /*
     *生成序列号
     */
    @RequestMapping(value = "/codeTest", method = RequestMethod.GET)
    @ResponseBody
    public String codeTest(String number){
        try {
            StringBuffer result;
            Grant grant = null;
            for(int z=0;z<Integer.valueOf(number);z++) {
                result = new StringBuffer();
                for (int i = 0; i < 15; i++) {
                    result.append(Integer.toHexString(new Random().nextInt(10)));
                }
                grant = new Grant();
                grant.setGrantCode(result.toString());
                grant.setGrantRecordId(31);
                grantService.saveGrant(grant);
            }
            return commonResponse.commonReturn(JSONObject.toJSONString("成功"));
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }


    /*
     *生成序列号
     */
    @RequestMapping(value = "/verificationTest", method = RequestMethod.GET)
    @ResponseBody
    public String verificationTest(){
        try {
            DateFormat dFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss"); //HH表示24小时制;
            Date date = new Date();
            List<String> list = new ArrayList<String>();
            list.add("026570291692507");
            list.add("177999884542976");
            list.add("799950368634572");
            list.add("456468765464654");
            list.add("587964654646344");
            list.add("789765465456887");
            list.add("213546587987976");
            list.add("686546546789564");
            list.add("879646465464645");
            list.add("431546487979796");
            List<Integer> idlist = new ArrayList<Integer>();
            idlist.add(30);
            idlist.add(31);
            int z = 0;
            int y = 0;
            for(int i=0;i<list.size();i++){
                ++y;
                if(grantService.getGrantCode(list.get(i),idlist)>0){
                    ++z;
                }
            }
            System.out.println("开始："+dFormat.format(date));
            System.out.println("次数："+y);
            System.out.println("不可以："+z);
            System.out.println("结束："+dFormat.format(new Date()));
            return commonResponse.commonReturn(JSONObject.toJSONString("成功"));
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

    /*
     *Mail测试
     */
    @RequestMapping(value = "/mailText", method = RequestMethod.GET)
    @ResponseBody
    public String mailText(String mail){
        String from = "2498956512@qq.com";// 发件人电子邮箱
        String host = "smtp.qq.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)
        Properties properties = System.getProperties();// 获取系统属性
        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");// 打开认证
        try {
            //QQ邮箱需要下面这段代码，163邮箱不需要
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("2498956512@qq.com", "pnuyrxbujwstdjgj"); // 发件人邮箱账号、授权码
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            // 2.3设置邮件主题
            message.setSubject("账号激活");
            // 2.4设置邮件内容
            String content = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1><h3><a href='http://localhost:8080/RegisterDemo/ActiveServlet?code="
                    + "123456" + "'>http://localhost:8080/RegisterDemo/ActiveServlet?code=" + "123456"
                    + "</href></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
            System.out.println("邮件成功发送!");

            return commonResponse.commonReturn(JSONObject.toJSONString("成功"));
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

}
